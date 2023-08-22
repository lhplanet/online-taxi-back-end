package com.sdu.servicepassengeruser.controller;

import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.request.VerificationCodeDTO;
import com.sdu.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author LHP
 * @description 用户控制类
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO) {

        // 接收参数（获取手机号）
        String passengerPhone = verificationCodeDTO.getPassengerPhone();

        return userService.loginOrRegister(passengerPhone);
    }

    @GetMapping("/user/{phone}")  // 防止feign的post转get的bug
    public ResponseResult getUser(@PathVariable("phone") String passengerPhone) {
        return userService.getUserByPhone(passengerPhone);

    }

}
