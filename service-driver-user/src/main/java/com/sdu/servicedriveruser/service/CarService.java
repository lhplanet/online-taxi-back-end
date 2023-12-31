package com.sdu.servicedriveruser.service;

import com.sdu.internalcommon.dto.Car;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.response.TerminalResponse;
import com.sdu.internalcommon.response.TrackResponse;
import com.sdu.servicedriveruser.mapper.CarMapper;
import com.sdu.servicedriveruser.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LHP
 * @description 车辆服务类
 */
@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ServiceMapClient serviceMapClient;

    public boolean addCar(Car car){
        LocalDateTime now = LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);
        // 保存车辆
        carMapper.insert(car);

        // 获得此车辆的终端id：tid
        ResponseResult<TerminalResponse> responseResult = serviceMapClient.addTerminal(car.getVehicleNo(), car.getId()+"");
        String tid = responseResult.getData().getTid();
        car.setTid(tid);

        // 获得此车辆的轨迹id：trid
        ResponseResult<TrackResponse> trackResponseResponseResult = serviceMapClient.addTrack(tid);
        String trid = trackResponseResponseResult.getData().getTrid();
        String trname = trackResponseResponseResult.getData().getTrname();

        car.setTrid(trid);
        car.setTrname(trname);

        carMapper.updateById(car);

        return true;
    }

    /**
     * 编辑车辆信息
     * @param car 车辆信息
     * @return 响应结果
     */
    public boolean updateCar(Car car){
        LocalDateTime now = LocalDateTime.now();
        car.setGmtModified(now);

        carMapper.updateById(car);
        return true;
    }

    /**
     * 删除车辆
     * @param carId 车辆id
     * @return 响应结果
     */
    public boolean deleteCar(Long carId){
        carMapper.deleteById(carId);
        // TODO: 删除终端和轨迹
        return true;
    }

    /**
     * 查询车辆列表
     * @param car 车辆信息
     * @return 响应结果
     */
    public List<Car> getCar(Car car){
        return carMapper.getCarList(car);
    }


    public ResponseResult<Car> getCarById(Long id){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);

        List<Car> cars = carMapper.selectByMap(map);

        if (cars.size() == 0) {
            // TODO: 这里应该返回什么信息？
            return ResponseResult.fail("没有此车辆");
        }
        return ResponseResult.success(cars.get(0));

    }

    public int getCarCount(){
        return carMapper.getCarCount();
    }

}
