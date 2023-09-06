package com.sdu.apiboss.remote;

import com.sdu.internalcommon.dto.Car;
import com.sdu.internalcommon.dto.OrderInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author LHP
 * @description
 */
@FeignClient(name = "service-order-1", url = "http://localhost:8089")
public interface ServiceOrderClient {

    @RequestMapping(method = RequestMethod.POST, value = "/order/list")
    public List<OrderInfo> getOrderList(@RequestBody OrderInfo orderInfo);

    @RequestMapping(method = RequestMethod.GET, value = "/order/order-count")
    public int getOrderCount();

}
