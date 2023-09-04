package com.sdu.apidriver.service;

import com.sdu.apidriver.remote.ServiceDriverUserClient;
import com.sdu.internalcommon.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LHP
 * @description 司机用户服务类
 */
@Service
public class UserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult updateUser(DriverUser driverUser){
        return serviceDriverUserClient.updateUser(driverUser);
    }

    /**
     * （根据司机id）查询司机信息
     * @param driverInfoVo
     * @return
     */
    public ResponseResult getUser(DriverInfoVo driverInfoVo){
        return serviceDriverUserClient.getUser(driverInfoVo);
    }

//    public ResponseResult getDriverById(Long driverId){
//        return serviceDriverUserClient.getDriverById(driverId);
//    }

    public ResponseResult changeWorkStatus(DriverUserWorkStatus driverUserWorkStatus){
        return serviceDriverUserClient.changeWorkStatus(driverUserWorkStatus);
    }

    public ResponseResult<DriverCarBindingRelationship> getDriverCarBindingRelationship(String driverPhone){
        // 根据driverPhone查询司机信息
        return serviceDriverUserClient.getDriverCarRelationShip(driverPhone);
    }

    public ResponseResult<Car> getCarInfo(Long carId) {
        return serviceDriverUserClient.getCarById(carId);
    }

    public ResponseResult<DriverUserWorkStatus> getWorkStatus(Long driverId){
        return serviceDriverUserClient.getWorkStatus(driverId);
    }


}
