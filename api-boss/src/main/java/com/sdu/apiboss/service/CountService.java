package com.sdu.apiboss.service;

import com.sdu.apiboss.remote.ServiceCarClient;
import com.sdu.apiboss.remote.ServiceDriverUserClient;
import com.sdu.apiboss.remote.ServiceOrderClient;
import com.sdu.apiboss.remote.ServicePassengerUserClient;
import com.sdu.internalcommon.dto.CountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LHP
 * @description
 */
@Service
public class CountService {

    @Autowired
    ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    ServicePassengerUserClient servicePassengerUserClient;

    @Autowired
    ServiceCarClient serviceCarClient;

    @Autowired
    ServiceOrderClient serviceOrderClient;


    public CountVo getHomeCount() {
        CountVo countVo = new CountVo();
        countVo.setDriverCount(serviceDriverUserClient.getDriverCount());
        countVo.setPassengerCount(servicePassengerUserClient.getPassengerCount());
        countVo.setCarCount(serviceCarClient.getCarCount());
        countVo.setOrderCount(serviceOrderClient.getOrderCount());
        return countVo;
    }


}
