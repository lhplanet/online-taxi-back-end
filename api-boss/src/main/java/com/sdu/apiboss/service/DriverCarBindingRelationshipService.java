package com.sdu.apiboss.service;

import com.sdu.apiboss.remote.ServiceDriverUserClient;
import com.sdu.internalcommon.dto.DriverCarBindingRelationship;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LHP
 * @description 司机车辆绑定关系服务类
 */
@Service
public class DriverCarBindingRelationshipService {

    @Autowired
    ServiceDriverUserClient serviceDriverUserClient;


    public ResultVo bind(DriverCarBindingRelationship driverCarBindingRelationship){
        return serviceDriverUserClient.bind(driverCarBindingRelationship);
    }

    public ResultVo unbind(DriverCarBindingRelationship driverCarBindingRelationship) {

        return serviceDriverUserClient.unbind(driverCarBindingRelationship);
    }
}
