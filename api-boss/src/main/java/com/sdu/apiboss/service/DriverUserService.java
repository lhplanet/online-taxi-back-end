package com.sdu.apiboss.service;

import com.sdu.apiboss.remote.ServiceDriverUserClient;
import com.sdu.internalcommon.dto.DriverUser;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.result.ResultUtils;
import com.sdu.internalcommon.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LHP
 * @description 司机用户业务类
 */
@Service
public class DriverUserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public boolean addDriverUser(DriverUser driverUser){
        return serviceDriverUserClient.addDriverUser(driverUser);
    }

    public boolean updateDriverUser(DriverUser driverUser){
        return serviceDriverUserClient.updateDriverUser(driverUser);
    }

    public boolean deleteDriverUser(Long driverId){
        return serviceDriverUserClient.deleteDriverUser(driverId);
    }

    public List<DriverUser> getDriverUser(DriverUser driverUser){
        return serviceDriverUserClient.getDriverUser(driverUser);
    }

}
