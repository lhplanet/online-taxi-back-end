package com.sdu.apiboss.remote;

import com.sdu.internalcommon.dto.Car;
import com.sdu.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author LHP
 * @description
 */
@FeignClient(name = "service-driver-user-2", url = "http://localhost:8086")
public interface ServiceCarClient {

    @RequestMapping(method = RequestMethod.POST, value = "/car")
    public boolean addCar(@RequestBody Car car);

    @RequestMapping(method = RequestMethod.PUT, value = "/car")
    public boolean updateCar(@RequestBody Car car);

    @RequestMapping(method = RequestMethod.DELETE, value = "/car")
    public boolean deleteCar(@RequestBody Long carId);

    @RequestMapping(method = RequestMethod.POST, value = "/car/list")
    public List<Car> getCar(@RequestBody Car car);

    @RequestMapping(method = RequestMethod.GET, value = "/car/car-count")
    public int getCarCount();

}
