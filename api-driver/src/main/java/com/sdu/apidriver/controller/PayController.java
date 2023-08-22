package com.sdu.apidriver.controller;

import com.sdu.apidriver.service.PayService;
import com.sdu.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LHP
 * @date 2023-07-14 22:58
 * @description
 */

@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    PayService payService;

    /**
     * 司机发起收款
     * @param orderId
     * @param price
     * @return
     */
    @PostMapping("/push-pay-info")
    public ResponseResult pushPayInfo(@RequestParam Long orderId , @RequestParam String price, @RequestParam Long passengerId){

        return payService.pushPayInfo(orderId,price,passengerId);
    }
}
