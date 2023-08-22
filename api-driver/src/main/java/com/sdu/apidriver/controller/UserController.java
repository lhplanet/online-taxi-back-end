package com.sdu.apidriver.controller;

import com.sdu.apidriver.service.UserService;
import com.sdu.internalcommon.dto.DriverUser;
import com.sdu.internalcommon.dto.DriverUserWorkStatus;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.dto.TokenResult;
import com.sdu.internalcommon.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LHP
 * @description 司机用户控制类
 */
@RestController
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

    @PostMapping("/driver-user-work-status")
    public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){

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
