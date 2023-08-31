package com.sdu.apidriver.controller;

import com.sdu.apidriver.service.UserService;
import com.sdu.internalcommon.dto.DriverUser;
import com.sdu.internalcommon.dto.DriverUserWorkStatus;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.dto.TokenResult;
import com.sdu.internalcommon.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LHP
 * @description 司机用户控制类
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 司机修改司机（自己）信息
     * @param driverUser 司机信息
     * @return ResponseResult
     */
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
        return userService.updateUser(driverUser);
    }

    /**
     * （根据手机号）查询司机信息
     * @param driverUser
     * @return
     */
    @GetMapping("/user-info")
    public ResponseResult getUser(@RequestBody DriverUser driverUser){
        System.out.println("==============driverUser = " + driverUser.getDriverPhone());
        return userService.getUser(driverUser);
    }

    @PostMapping("/driver-user-work-status")
    public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){

        System.out.println("==============driverId = " + driverUserWorkStatus.getDriverId());
        System.out.println("==============workStatus = " + driverUserWorkStatus.getWorkStatus());

        return userService.changeWorkStatus(driverUserWorkStatus);
    }

    /**
     * 根据司机token查询 司机和车辆绑定关系
     * @param request
     * @return
     */
    @GetMapping("/driver-car-binding-relationship")
    public ResponseResult getDriverCarBindingRelationship(HttpServletRequest request){

        String authorization = request.getHeader("Authorization");
        TokenResult tokenResult = JwtUtils.checkToken(authorization);
        String driverPhone = tokenResult.getPhone();

        return userService.getDriverCarBindingRelationship(driverPhone);

    }

    @GetMapping("/work-status")
    public ResponseResult<DriverUserWorkStatus> getWorkStatus(Long driverId){
        return userService.getWorkStatus(driverId);
    }

}
