package com.sdu.apipassenger.controller;

import com.sdu.apipassenger.service.UserService;
import com.sdu.internalcommon.dto.PassengerUser;
import com.sdu.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LHP
 * @date 2023-07-11 21:35
 * @description
 */

@RestController
@Slf4j
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
