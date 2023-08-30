package com.sdu.apiboss.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdu.apiboss.service.DriverUserService;
import com.sdu.internalcommon.dto.DriverUser;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.result.ResultUtils;
import com.sdu.internalcommon.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LHP
 * @description 司机用户控制类
 */
@RestController
@RequestMapping("/driver-user")
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    /**
     * 添加司机
     * @param driverUser 司机信息
     * @return 响应结果
     */
    @PostMapping("/add")
    public ResultVo addDriverUser(@RequestBody DriverUser driverUser){
        String driverName = driverUser.getDriverName();
        driverUser.setDriverName("");
        List<DriverUser> list = driverUserService.getDriverUser(driverUser);
        if(list != null && list.size() > 0){
            return ResultUtils.error("该手机号司机已存在!");
        }
        driverUser.setDriverName(driverName);
        boolean result = driverUserService.addDriverUser(driverUser);
        if(!result){
            return ResultUtils.error("新增失败!");
        }
        return ResultUtils.success("新增成功!");
    }

    /**
     * 修改司机信息
     * @param driverUser 司机信息
     * @return 响应结果
     */
    @PutMapping("/edit")
    public ResultVo updateDriverUser(@RequestBody DriverUser driverUser){
        boolean result = driverUserService.updateDriverUser(driverUser);
        if(!result){
            return ResultUtils.error("编辑失败!");
        }
        return ResultUtils.success("编辑成功!");
    }

    /**
     * 删除司机信息
     * @param driverId 司机id
     * @return 响应结果
     */
    @DeleteMapping("/{driverId}")
    public ResultVo deleteDriverUser(@PathVariable("driverId") Long driverId){
        boolean result = driverUserService.deleteDriverUser(driverId);
        if(!result){
            return ResultUtils.error("删除失败!");
        }
        return ResultUtils.success("删除成功!");
    }

    /**
     * 查询司机列表
     * @param driverUser 司机信息
     * @return 响应结果
     */
    @PostMapping("/list")
    public ResultVo getDriverUser(@RequestBody DriverUser driverUser){
        List<DriverUser> list = driverUserService.getDriverUser(driverUser);
        return  ResultUtils.success("查询成功", list);
    }

    /**
     * 查询司机信息
     * @param driverId 司机id
     * @return 响应结果
     */
/*    @GetMapping("/{driverId}")
    public ResultVo getDriverUserById(@PathVariable("driverId") Long driverId){
        DriverUser driverUser = driverUserService.getDriverUserById(driverId);
        return  ResultUtils.success("查询成功", driverUser);
    }*/

    /**
     * 查询司机信息
     * @param driverPhone 司机手机号
     * @return 响应结果
     */
/*    @GetMapping("/phone/{driverPhone}")
    public ResultVo getDriverUserByPhone(@PathVariable("driverPhone") String driverPhone){
        DriverUser driverUser = driverUserService.getDriverUserByPhone(driverPhone);
        return  ResultUtils.success("查询成功", driverUser);
    }*/

}
