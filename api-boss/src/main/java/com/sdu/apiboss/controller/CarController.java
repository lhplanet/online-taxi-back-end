package com.sdu.apiboss.controller;

import com.sdu.apiboss.service.CarService;
import com.sdu.internalcommon.dto.Car;
import com.sdu.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LHP
 * @description 车辆控制类
 */
@RestController
public class CarController {

    @Autowired
    CarService carService;

    /**
     * 添加车辆
     * @param car 车辆信息
     * @return 响应结果
     */
    @PostMapping("/car")
    public ResponseResult car(@RequestBody Car car){
        return carService.addCar(car);
    }

}
