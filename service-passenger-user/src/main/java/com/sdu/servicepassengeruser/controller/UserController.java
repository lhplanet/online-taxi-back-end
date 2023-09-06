package com.sdu.servicepassengeruser.controller;

import com.sdu.internalcommon.dto.PassengerUser;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.request.VerificationCodeDTO;
import com.sdu.servicepassengeruser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LHP
 * @description 用户控制类
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/add")
    public boolean addUser(@RequestBody PassengerUser passengerUser){
        return userService.addPassengerUser(passengerUser);
    }

    @PutMapping("/user")
    public boolean updateUser(@RequestBody PassengerUser passengerUser){
        return userService.updatePassengerUser(passengerUser);
    }

    @DeleteMapping("/user")
    public boolean deletePassengerUser(@RequestBody Long passengerId){
        return userService.deletePassengerUser(passengerId);
    }

    @PostMapping("/user/list")
    public List<PassengerUser> getUser(@RequestBody PassengerUser passengerUser){
        return userService.getPassengerUser(passengerUser);
    }


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

    @GetMapping("/user/passenger-count")
    public int getPassengerCount(){
        return userService.getPassengerCount();
    }

}
