package com.sdu.serviceprice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdu.internalcommon.constant.CommonStatusEnum;
import com.sdu.internalcommon.dto.PriceRule;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.request.ForecastPriceDTO;
import com.sdu.internalcommon.response.DirectionResponse;
import com.sdu.internalcommon.response.ForecastPriceResponse;
import com.sdu.internalcommon.util.BigDecimalUtils;
import com.sdu.serviceprice.mapper.PriceRuleMapper;
import com.sdu.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LHP
 * @description 价格服务类
 */
@Service
@Slf4j
public class PriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private PriceRuleMapper priceRuleMapper;

    /**
     * 根据出发地和目的地经纬度，预估价格
     * @param depLongitude 出发地经度
     * @param depLatitude 出发地纬度
     * @param destLongitude 目的地经度
     * @param destLatitude 目的地纬度
     * @return 预估价格
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude, String cityCode, String vehicleType) {

        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);

        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);

        // 距离
        Integer distance = direction.getData().getDistance();
        // 时长
        Integer duration = direction.getData().getDuration();
        log.info("距离：" + distance + "，时长：" + duration);

        // 读取计价规则
        log.info("读取计价规则");
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("city_code", cityCode);
        queryMap.put("vehicle_type", vehicleType);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("city_code",cityCode);
        queryWrapper.eq("vehicle_type",vehicleType);
        queryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        if (priceRules.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        PriceRule priceRule = priceRules.get(0); // 计价规则

        // 根据距离、时长和计价规则，计算价格
        log.info("根据距离、时长和计价规则，计算价格");
        double price = getPrice(distance, duration, priceRule);

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        forecastPriceResponse.setCityCode(cityCode);
        forecastPriceResponse.setVehicleType(vehicleType);
        forecastPriceResponse.setFareType(priceRule.getFareType());
        forecastPriceResponse.setFareVersion(priceRule.getFareVersion());

        return ResponseResult.success(forecastPriceResponse);
    }

    /**
     * 计算实际价格
     * @param distance
     * @param duration
     * @param cityCode
     * @param vehicleType
     * @return
     */
    public ResponseResult<Double> calculatePrice( Integer distance ,  Integer duration, String cityCode, String vehicleType){
        // 查询计价规则
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("city_code",cityCode);
        queryWrapper.eq("vehicle_type",vehicleType);
        queryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        if (priceRules.size() == 0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        PriceRule priceRule = priceRules.get(0);

        log.info("根据距离、时长和计价规则，计算价格"); // TODO: 根据车型不同，计算价格不同

        double price = getPrice(distance, duration, priceRule);
        return ResponseResult.success(price);
    }

    /**
     * 根据距离、时长和计价规则，计算价格
     * @param distance 距离
     * @param duration 时长
     * @param priceRule 计价规则
     * @return
     */
    private double getPrice(Integer distance, Integer duration, PriceRule priceRule) {
        double price = 0;

        // TODO: 再看看这里的计算逻辑
        // 起步价
        double startFare = priceRule.getStartFare();
        price = BigDecimalUtils.add(price, startFare);

        // 里程费
        // 超出起步里程（总里程 - 起步里程）
        // 总里程 m -> km
        double distanceMile = BigDecimalUtils.divide(distance, 1000);
        // 起步里程 km
        double startMile = (double)priceRule.getStartMile();
        // 总里程 - 起步里程
        double distanceSubtract = BigDecimalUtils.substract(distanceMile, startMile);
        // 最终收费的里程 km
        double mile = distanceSubtract > 0 ? distanceSubtract : 0;
        // 计程单价 元/km
        double unitPricePerMile = priceRule.getUnitPricePerMile();
        // 里程费（最终收费的里程 * 计程单价）
        double mileFare = BigDecimalUtils.multiply(mile, unitPricePerMile);
        price = BigDecimalUtils.add(price, mileFare);

        // 时长费
        // 时长 s -> min
        double timeMinute = BigDecimalUtils.divide(duration, 60);
        // 计时单价 元/min
        double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        // 时长费（时长 * 计时单价）
        double timeFare = BigDecimalUtils.multiply(timeMinute, unitPricePerMinute);
        price = BigDecimalUtils.add(price, timeFare);

        BigDecimal priceBigDecimal = new BigDecimal(price);
        priceBigDecimal = priceBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);

        return priceBigDecimal.doubleValue();

        /*BigDecimal price = new BigDecimal(0);

        // 起步价
        Double startFare = priceRule.getStartFare();
        BigDecimal startFareBigDecimal = new BigDecimal(startFare);
        price = price.add(startFareBigDecimal);

        // 里程费
        // 超出起步里程（总里程 - 起步里程）
        // 总里程 m
        BigDecimal distanceBigDecimal = new BigDecimal(distance);
        // 总里程 km
        BigDecimal distanceKmBigDecimal = distanceBigDecimal.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
        // 起步里程 km
        Integer startMile = priceRule.getStartMile();
        BigDecimal startKmBigDecimal = new BigDecimal(startMile);
        // 总里程 - 起步里程
        double distanceSubtract = distanceKmBigDecimal.subtract(startKmBigDecimal).doubleValue();
        // 最终收费的里程 km
        Double mile = distanceSubtract > 0 ? distanceSubtract : 0;
        BigDecimal mileBigDecimal = new BigDecimal(mile);
        // 计程单价 元/km
        Double unitPricePerMile = priceRule.getUnitPricePerMile();
        BigDecimal unitPricePerMileBigDecimal = new BigDecimal(unitPricePerMile);
        // 里程费（最终收费的里程 * 计程单价）
        BigDecimal mileFare = mileBigDecimal.multiply(unitPricePerMileBigDecimal).setScale(2, BigDecimal.ROUND_HALF_UP);
        price = price.add(mileFare);

        // 时长费
        // 时长 s
        BigDecimal time = new BigDecimal(duration);
        // 时长 min
        BigDecimal timeBigDecimal = time.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
        // 计时单价 元/min
        Double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        BigDecimal unitPricePerMinuteBigDecimal = new BigDecimal(unitPricePerMinute);
        // 时长费（时长 * 计时单价）
        BigDecimal timeFare = timeBigDecimal.multiply(unitPricePerMinuteBigDecimal);
        price = price.add(timeFare).setScale(2, BigDecimal.ROUND_HALF_UP);

        return price.doubleValue();*/
    }

/*    public static void main(String[] args) {
        PriceRule priceRule = new PriceRule();
        priceRule.setStartFare(10.00);
        priceRule.setStartMile(3);
        priceRule.setUnitPricePerMile(1.80);
        priceRule.setUnitPricePerMinute(0.50);
        System.out.println(getPrice(6500, 1800, priceRule));
    }*/

}
