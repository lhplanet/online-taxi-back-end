package com.sdu.servicedriveruser.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdu.internalcommon.constant.DriverCarConstants;
import com.sdu.internalcommon.dto.DriverCarBindingRelationship;
import com.sdu.internalcommon.dto.DriverInfoVo;
import com.sdu.internalcommon.dto.DriverUser;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.response.DriverUserExistsResponse;
import com.sdu.internalcommon.response.OrderDriverResponse;
import com.sdu.internalcommon.result.ResultUtils;
import com.sdu.internalcommon.result.ResultVo;
import com.sdu.servicedriveruser.service.DriverCarBindingRelationshipService;
import com.sdu.servicedriveruser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LHP
 * @description 司机用户控制类
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    /**
     * 添加司机
     * @param driverUser 司机信息
     * @return 响应结果
     */
    @PostMapping("/user")
    public boolean addUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * 修改司机信息
     * @param driverUser 司机信息
     * @return 响应结果
     */
    @PutMapping("/user")
    public boolean updateUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.updateDriverUser(driverUser);
    }

    @DeleteMapping("/user")
    public boolean deleteDriverUser(@RequestBody Long driverId){
        return driverUserService.deleteDriverUser(driverId);
    }

    @PostMapping("/user/list")
    public List<DriverUser> getUserList(@RequestBody DriverUser driverUser){
        return driverUserService.getDriverUser(driverUser);
    }

    /**
     * （根据司机id）查询司机信息
     * @param
     * @return
     */
    @PostMapping("/user-info")
    public ResponseResult getUser(@RequestBody DriverInfoVo driverInfoVo){
        // 根据手机号查询司机信息
        ResponseResult<DriverUser> driverUserById = driverUserService.getDriverUserById(driverInfoVo);
        DriverUser driverUserDb = driverUserById.getData();
        return ResponseResult.success(driverUserDb);
    }

//    @GetMapping("/driver/{driverId}")
//    public ResponseResult getDriverById(@PathVariable("driverId") Long driverId){
//        // 根据手机号查询司机信息
//        ResponseResult<DriverUser> driverUserById = driverUserService.getDriverUserById(driverId.toString());
//        DriverUser driverUserDb = driverUserById.getData();
//        return ResponseResult.success(driverUserDb);
//    }


    /**
     * 查询司机是否存在，如果需要按照司机的多个条件做查询，那么此处用 /user
     * @param driverPhone 司机手机号
     * @return 响应结果
     */
    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> checkDriver(@PathVariable("driverPhone") String driverPhone){

        ResponseResult<DriverUser> driverUserByPhone = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverUserDb = driverUserByPhone.getData();

        DriverUserExistsResponse response = new DriverUserExistsResponse();

        int ifExists = DriverCarConstants.DRIVER_EXISTS;
        if (driverUserDb == null){
            ifExists = DriverCarConstants.DRIVER_NOT_EXISTS;
            response.setDriverPhone(driverPhone);
            response.setIfExists(ifExists);
        }else {
            response.setDriverPhone(driverUserDb.getDriverPhone());
            response.setIfExists(ifExists);
        }

        return ResponseResult.success(response);
    }

    /**
     * 根据车辆Id查询订单需要的司机信息
     * @param carId
     * @return
     */
    @GetMapping("/get-available-driver/{carId}")
    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId){
        return driverUserService.getAvailableDriver(carId);
    }

    @Autowired
    DriverCarBindingRelationshipService driverCarBindingRelationshipService;

    /**
     * 根据司机手机号查询司机和车辆绑定关系
     * @param driverPhone
     * @return
     */
    @GetMapping("/driver-car-binding-relationship")
    public ResponseResult<DriverCarBindingRelationship> getDriverCarRelationShip(@RequestParam String driverPhone){
        return driverCarBindingRelationshipService.getDriverCarRelationShipByDriverPhone(driverPhone);
    }

    @GetMapping("/user/driver-count")
    public int getDriverCount(){
        return driverUserService.getDriverCount();
    }


}
