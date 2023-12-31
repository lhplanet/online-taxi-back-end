package com.sdu.apidriver.service;

import com.sdu.apidriver.remote.ServiceDriverUserClient;
import com.sdu.apidriver.remote.ServiceMapClient;
import com.sdu.internalcommon.dto.Car;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.request.ApiDriverPointRequest;
import com.sdu.internalcommon.request.PointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LHP
 * @date 2023-07-14 0:17
 * @description
 */

@Service
public class PointService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult upload(ApiDriverPointRequest apiDriverPointRequest){
        // 获取carId
        Long carId = apiDriverPointRequest.getCarId();

        System.out.println("==================carId: " + carId);

        // 通过carId 获取 tid、trid，调用 service-driver-user的接口
        ResponseResult<Car> carById = serviceDriverUserClient.getCarById(carId);
        Car car = carById.getData();
        String tid = car.getTid();
        String trid = car.getTrid();

        System.out.println("==================tid: " + tid);
        System.out.println("==================trid: " + trid);
        System.out.println("==================car: " + car);

        // 调用地图去上传
        PointRequest pointRequest = new PointRequest();
        pointRequest.setTid(tid);
        pointRequest.setTrid(trid);
        pointRequest.setPoints(apiDriverPointRequest.getPoints());

        return serviceMapClient.upload(pointRequest);

    }
}
