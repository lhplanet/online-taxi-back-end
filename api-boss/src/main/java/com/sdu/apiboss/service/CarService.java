package com.sdu.apiboss.service;

import com.sdu.apiboss.remote.ServiceDriverUserClient;
import com.sdu.internalcommon.dto.Car;
import com.sdu.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LHP
 * @description 车辆服务类
 */
@Service
public class CarService {

    @Autowired
    ServiceDriverUserClient serviceDriverUserClient;

    /**
     * 添加车辆
     * @param car 车辆信息
     * @return 响应结果
     */
    public ResponseResult addCar(Car car){
        return serviceDriverUserClient.addCar(car);
    }

}
