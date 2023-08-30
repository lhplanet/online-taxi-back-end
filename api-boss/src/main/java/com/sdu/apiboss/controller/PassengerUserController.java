package com.sdu.apiboss.controller;

import com.sdu.apiboss.service.PassengerUserService;
import com.sdu.internalcommon.dto.PassengerUser;
import com.sdu.internalcommon.result.ResultUtils;
import com.sdu.internalcommon.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LHP
 * @description
 */
@RestController
@RequestMapping("/passenger-user")
public class PassengerUserController {

    @Autowired
    private PassengerUserService passengerUserService;

    /**
     * 添加乘客
     * @param passengerUser 乘客信息
     * @return 响应结果
     */
    @PostMapping("/add")
    public ResultVo addPassengerUser(@RequestBody PassengerUser passengerUser){
        String passengerName = passengerUser.getPassengerName();
        passengerUser.setPassengerName("");
        List<PassengerUser> list = passengerUserService.getPassengerUser(passengerUser);
        if(list != null && list.size() > 0){
            return ResultUtils.error("该手机号乘客已存在!");
        }
        passengerUser.setPassengerName(passengerName);
        boolean result = passengerUserService.addPassengerUser(passengerUser);
        if(!result){
            return ResultUtils.error("新增失败!");
        }
        return ResultUtils.success("新增成功!");
    }

    /**
     * 修改乘客信息
     * @param passengerUser 乘客信息
     * @return 响应结果
     */
    @PutMapping("/edit")
    public ResultVo updatePassengerUser(@RequestBody PassengerUser passengerUser){
        boolean result = passengerUserService.updatePassengerUser(passengerUser);
        if(!result){
            return ResultUtils.error("编辑失败!");
        }
        return ResultUtils.success("编辑成功!");
    }

    /**
     * 删除乘客信息
     * @param passengerId 乘客id
     * @return 响应结果
     */
    @DeleteMapping("/{passengerId}")
    public ResultVo deletePassengerUser(@PathVariable("passengerId") Long passengerId){
        boolean result = passengerUserService.deletePassengerUser(passengerId);
        if(!result){
            return ResultUtils.error("删除失败!");
        }
        return ResultUtils.success("删除成功!");
    }

    /**
     * 查询乘客列表
     * @param passengerUser 乘客信息
     * @return 响应结果
     */
    @PostMapping("/list")
    public ResultVo getPassengerUser(@RequestBody PassengerUser passengerUser){
        List<PassengerUser> list = passengerUserService.getPassengerUser(passengerUser);
        return  ResultUtils.success("查询成功", list);
    }

}
