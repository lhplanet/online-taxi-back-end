package com.sdu.apiboss.service;

import com.sdu.apiboss.remote.ServiceCarClient;
import com.sdu.apiboss.remote.ServiceDriverUserClient;
import com.sdu.internalcommon.dto.Car;
import com.sdu.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LHP
 * @description 车辆服务类
 */
@Service
public class CarService {

    @Autowired
    ServiceCarClient serviceCarClient;

    public boolean addCar(Car car){
        return serviceCarClient.addCar(car);
    }

    public boolean updateCar(Car car){
        return serviceCarClient.updateCar(car);
    }

    public boolean deleteCar(Long carId){
        return serviceCarClient.deleteCar(carId);
    }

    public List<Car> getCar(Car car){
        return serviceCarClient.getCar(car);
    }

}
