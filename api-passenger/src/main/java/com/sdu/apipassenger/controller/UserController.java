package com.sdu.apipassenger.controller;

import com.sdu.apipassenger.service.UserService;
import com.sdu.internalcommon.dto.DriverUser;
import com.sdu.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LHP
 * @date 2023-07-11 21:35
 * @description
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseResult getUsers(HttpServletRequest request) {

        // 从http请求中获取accessToken
        String accessToken = request.getHeader("Authorization");

        return userService.getUserByAccessToken(accessToken);
    }

    /**
     * 根据乘客手机号查询司机信息
     * @param passengerPhone
     * @return
     */
    @GetMapping("/user")
    public ResponseResult getUser(String passengerPhone){
        return userService.getUser(passengerPhone);
    }
}
