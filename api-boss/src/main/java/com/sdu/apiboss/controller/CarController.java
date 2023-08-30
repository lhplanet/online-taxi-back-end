package com.sdu.apiboss.controller;

import com.sdu.apiboss.service.CarService;
import com.sdu.internalcommon.dto.Car;
import com.sdu.internalcommon.result.ResultUtils;
import com.sdu.internalcommon.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LHP
 * @description 车辆控制类
 */
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    /**
     * 添加车辆
     * @param car 车辆信息
     * @return 响应结果
     */
    @PostMapping("/add")
    public ResultVo addCar(@RequestBody Car car){
        String ownerName = car.getOwnerName();
        car.setOwnerName("");
        List<Car> list = carService.getCar(car);
        if(list != null && list.size() > 0){
            return ResultUtils.error("该车辆号牌已存在!");
        }
        car.setOwnerName(ownerName);
        boolean result = carService.addCar(car);
        if(!result){
            return ResultUtils.error("新增失败!");
        }
        return ResultUtils.success("新增成功!");
    }

    /**
     * 修改车辆信息
     * @param car 车辆信息
     * @return 响应结果
     */
    @PutMapping("/edit")
    public ResultVo updateCar(@RequestBody Car car){
        boolean result = carService.updateCar(car);
        if(!result){
            return ResultUtils.error("编辑失败!");
        }
        return ResultUtils.success("编辑成功!");
    }

    /**
     * 删除车辆信息
     * @param carId 车辆id
     * @return 响应结果
     */
    @DeleteMapping("/{carId}")
    public ResultVo deleteCar(@PathVariable("carId") Long carId){
        boolean result = carService.deleteCar(carId);
        if(!result){
            return ResultUtils.error("删除失败!");
        }
        return ResultUtils.success("删除成功!");
    }

    /**
     * 查询车辆列表
     * @param car 车辆信息
     * @return 响应结果
     */
    @PostMapping("/list")
    public ResultVo getCar(@RequestBody Car car){
        List<Car> list = carService.getCar(car);
        return ResultUtils.success("查询成功", list);
    }


}
