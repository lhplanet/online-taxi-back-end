package com.sdu.servicedriveruser.controller;


import com.sdu.internalcommon.dto.Car;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.servicedriveruser.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author LHP
 * @description 车辆控制类
 */
@RestController
public class CarController {
    @Autowired
    CarService carService;

    @PostMapping("/car")
    public boolean addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @PutMapping("/car")
    public boolean updateCar(@RequestBody Car car){
        return carService.updateCar(car);
    }

    @DeleteMapping("/car")
    public boolean deleteCar(@RequestBody Long carId){
        return carService.deleteCar(carId);
    }

    @PostMapping("/car/list")
    public List<Car> getCar(@RequestBody Car car){
        return carService.getCar(car);
    }

    // TODO: 这个没有用了？
    @GetMapping("/car")
    public ResponseResult<Car> getCarById(Long carId){
        return carService.getCarById(carId);
    }

}
