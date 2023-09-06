package com.sdu.apidriver.service;

import com.sdu.apidriver.remote.ServiceOrderClient;
import com.sdu.apidriver.remote.ServiceSsePushClient;
import com.sdu.internalcommon.constant.IdentityConstants;
import com.sdu.internalcommon.constant.OrderConstants;
import com.sdu.internalcommon.dto.OrderInfo;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.request.OrderRequest;
import com.sdu.internalcommon.request.PushRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author LHP
 * @description
 */
@Service
public class ApiDriverOrderInfoService {

    @Autowired
    ServiceOrderClient serviceOrderClient;

    @Autowired
    ServiceSsePushClient serviceSsePushClient;

    public ResponseResult toPickUpPassenger(OrderRequest orderRequest){

        // 封装消息
        JSONObject message = new JSONObject();
        message.put("orderState", OrderConstants.DRIVER_TO_PICK_UP_PASSENGER);

        PushRequest pushRequest = new PushRequest();
        pushRequest.setContent(message.toString());
        pushRequest.setUserId(orderRequest.getPassengerId());
        pushRequest.setIdentity(IdentityConstants.PASSENGER_IDENTITY);

        // 推送消息
        serviceSsePushClient.push(pushRequest);

        return serviceOrderClient.toPickUpPassenger(orderRequest);
    }

    public ResponseResult arrivedDeparture(OrderRequest orderRequest){

        // 封装消息
        JSONObject message = new JSONObject();
        message.put("orderState", OrderConstants.DRIVER_ARRIVED_DEPARTURE);

        PushRequest pushRequest = new PushRequest();
        pushRequest.setContent(message.toString());
        pushRequest.setUserId(orderRequest.getPassengerId());
        pushRequest.setIdentity(IdentityConstants.PASSENGER_IDENTITY);

        // 推送消息
        serviceSsePushClient.push(pushRequest);

        return serviceOrderClient.arrivedDeparture(orderRequest);
    }

    /**
     * 司机接到乘客
     * @param orderRequest
     * @return
     */
    public ResponseResult pickUpPassenger(@RequestBody OrderRequest orderRequest){

        // 封装消息
        JSONObject message = new JSONObject();
        message.put("orderState", OrderConstants.PICK_UP_PASSENGER);

        PushRequest pushRequest = new PushRequest();
        pushRequest.setContent(message.toString());
        pushRequest.setUserId(orderRequest.getPassengerId());
        pushRequest.setIdentity(IdentityConstants.PASSENGER_IDENTITY);

        // 推送消息
        serviceSsePushClient.push(pushRequest);

        return  serviceOrderClient.pickUpPassenger(orderRequest);
    }

    public ResponseResult passengerGetoff(OrderRequest orderRequest){

        // 封装消息
        JSONObject message = new JSONObject();
        message.put("orderState", OrderConstants.PASSENGER_GETOFF);

        PushRequest pushRequest = new PushRequest();
        pushRequest.setContent(message.toString());
        pushRequest.setUserId(orderRequest.getPassengerId());
        pushRequest.setIdentity(IdentityConstants.PASSENGER_IDENTITY);

        // 推送消息
        serviceSsePushClient.push(pushRequest);

        return serviceOrderClient.passengerGetoff(orderRequest);
    }

    public ResponseResult cancel(Long orderId){
        return  serviceOrderClient.cancel(orderId, IdentityConstants.DRIVER_IDENTITY);
    }

    public ResponseResult<OrderInfo> detail(Long orderId){
        return serviceOrderClient.detail(orderId);
    }


    public ResponseResult<OrderInfo> currentOrder(String phone , String identity){
        return serviceOrderClient.current(phone,identity);
    }
}
