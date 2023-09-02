package com.sdu.apiboss.service;

import com.sdu.apiboss.remote.ServiceOrderClient;
import com.sdu.internalcommon.dto.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LHP
 * @description
 */
@Service
public class OrderService {
    @Autowired
    ServiceOrderClient serviceOrderClient;

    public List<OrderInfo> getOrderList(OrderInfo orderInfo){
        return serviceOrderClient.getOrderList(orderInfo);
    }
}
