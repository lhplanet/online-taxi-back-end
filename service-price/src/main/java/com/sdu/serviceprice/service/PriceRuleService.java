package com.sdu.serviceprice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdu.internalcommon.constant.CommonStatusEnum;
import com.sdu.internalcommon.dto.PriceRule;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.result.ResultUtils;
import com.sdu.internalcommon.result.ResultVo;
import com.sdu.serviceprice.mapper.PriceRuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LHP
 * @description 计价规则service
 */
@Service
public class PriceRuleService {

    @Autowired
    PriceRuleMapper priceRuleMapper;

    public ResultVo addPriceRule(PriceRule priceRule) {

        // 拼接fareType
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode + "$" + vehicleType;
        priceRule.setFareType(fareType);

        // 添加版本号
        // 问题1：增加了版本号，前面的两个字段无法唯一确定一条记录，问题2：找最大的版本号，需要排序

        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        queryWrapper.eq("vehicle_type", vehicleType);
        queryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        Integer fareVersion = 0;
        if (priceRules.size() > 0) {
            return ResultUtils.error("新增失败：已存在该计价规则");
        }
        priceRule.setFareVersion(++fareVersion);


        priceRuleMapper.insert(priceRule);
        return ResultUtils.success("新增成功");
    }

    public boolean updatePriceRule(PriceRule priceRule){
        // 拼接fareType
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode + "$" + vehicleType;
        priceRule.setFareType(fareType);

        // 添加版本号
        // 问题1：增加了版本号，前面的两个字段无法唯一确定一条记录，问题2：找最大的版本号，需要排序

        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code",cityCode);
        queryWrapper.eq("vehicle_type",vehicleType);
        queryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        Integer fareVersion = 0;
        if (priceRules.size()>0){
            PriceRule lasterPriceRule = priceRules.get(0);
            Double unitPricePerMile = lasterPriceRule.getUnitPricePerMile();
            Double unitPricePerMinute = lasterPriceRule.getUnitPricePerMinute();
            Double startFare = lasterPriceRule.getStartFare();
            Integer startMile = lasterPriceRule.getStartMile();

            if (unitPricePerMile.doubleValue() == priceRule.getUnitPricePerMile().doubleValue()
                    && unitPricePerMinute.doubleValue() == priceRule.getUnitPricePerMinute().doubleValue()
                    && startFare.doubleValue() == priceRule.getStartFare().doubleValue()
                    && startMile.intValue() == priceRule.getStartMile().intValue()){
                return false;
            }


            fareVersion = lasterPriceRule.getFareVersion();
        }
        priceRule.setFareVersion(++fareVersion);


        priceRuleMapper.insert(priceRule);
        return true;
    }

    public boolean deletePriceRule(PriceRule priceRule){
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fare_type",priceRule.getFareType());
        queryWrapper.eq("fare_version",priceRule.getFareVersion());

        priceRuleMapper.delete(queryWrapper);
        return true;
    }

    public List<PriceRule> getPriceRuleList(PriceRule priceRule) {
        return priceRuleMapper.getPriceRuleList(priceRule);
    }


    public ResponseResult<PriceRule> getNewestVersion(String fareType){
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fare_type",fareType);

        queryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);

        if (priceRules.size() > 0){
            return ResponseResult.success(priceRules.get(0));
        }else {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }


    }


    public ResponseResult<Boolean> isNew(String fareType, int fareVersion){
        ResponseResult<PriceRule> newestVersionPriceRule = getNewestVersion(fareType);
        if (newestVersionPriceRule.getCode() == CommonStatusEnum.PRICE_RULE_EMPTY.getCode()){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }

        PriceRule priceRule = newestVersionPriceRule.getData();
        Integer fareVersionDB = priceRule.getFareVersion();
        if (fareVersionDB > fareVersion){
            return ResponseResult.success(false);
        }else {
            return ResponseResult.success(true);
        }

    }

    public ResponseResult<Boolean> ifExists(PriceRule priceRule){
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();

        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code",cityCode);
        queryWrapper.eq("vehicle_type",vehicleType);
        queryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);

        if (priceRules.size() > 0){
            return ResponseResult.success(true);
        }else {
            return ResponseResult.success(false);
        }
    }

}
