package com.sdu.apipassenger.service;

import com.sdu.apipassenger.remote.ServiceOrderClient;
import com.sdu.apipassenger.remote.ServicePushClient;
import com.sdu.internalcommon.constant.IdentityConstants;
import com.sdu.internalcommon.dto.OrderInfo;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.request.OrderRequest;
import com.sdu.internalcommon.request.PushRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LHP
 * @description 订单service
 */
@Service
public class OrderService {

    @Autowired
    ServiceOrderClient serviceOrderClient;

    @Autowired
    ServicePushClient servicePushClient;

    public ResponseResult add(OrderRequest orderRequest){
        return serviceOrderClient.add(orderRequest);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    public ResponseResult cancel(Long orderId){
        return serviceOrderClient.cancel(orderId, IdentityConstants.PASSENGER_IDENTITY);
    }

    public ResponseResult<OrderInfo> detail(Long orderId){
        return serviceOrderClient.detail(orderId);
    }

    public ResponseResult<OrderInfo> currentOrder(String phone , String identity){
        return serviceOrderClient.current(phone,identity);
    }

    public ResponseResult pdBegin(Long driverId){
        // 封装消息
        JSONObject message = new JSONObject();
        message.put("driverId",driverId);
//        message.put("orderId",orderId);

        PushRequest pushRequest = new PushRequest();
        pushRequest.setContent(message.toString());
        pushRequest.setUserId(driverId);
        pushRequest.setIdentity(IdentityConstants.DRIVER_IDENTITY);

        // 推送消息
        servicePushClient.pdBegin(pushRequest);

        return ResponseResult.success();
    }

}
