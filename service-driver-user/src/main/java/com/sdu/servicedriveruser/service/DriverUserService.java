package com.sdu.servicedriveruser.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdu.internalcommon.constant.CommonStatusEnum;
import com.sdu.internalcommon.constant.DriverCarConstants;
import com.sdu.internalcommon.dto.*;
import com.sdu.internalcommon.response.OrderDriverResponse;
import com.sdu.servicedriveruser.mapper.CarMapper;
import com.sdu.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import com.sdu.servicedriveruser.mapper.DriverUserMapper;
import com.sdu.servicedriveruser.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LHP
 * @description 司机用户业务类
 */
@Service
public class DriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    @Autowired
    private CarMapper carMapper;

    public ResponseResult testGetDriverUser(){
        return ResponseResult.success(driverUserMapper.selectById(1));
    }


    /**
     * 添加司机
     * @param driverUser 司机信息
     * @return 响应结果
     */
    public boolean addDriverUser(DriverUser driverUser){

        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);

        // TODO: 是否要加上判断创建成功与否
        driverUserMapper.insert(driverUser);

        // 初始化 司机工作状态表
        DriverUserWorkStatus driverUserWorkStatus = new DriverUserWorkStatus();
        driverUserWorkStatus.setDriverId(driverUser.getId());
        driverUserWorkStatus.setWorkStatus(DriverCarConstants.DRIVER_WORK_STATUS_STOP);
        driverUserWorkStatus.setGmtModified(now);
        driverUserWorkStatus.setGmtCreate(now);
        // TODO: 是否要加上判断创建成功与否
        driverUserWorkStatusMapper.insert(driverUserWorkStatus);

        return true;
    }

    /**
     * 编辑司机信息
     * @param driverUser 司机信息
     * @return 响应结果
     */
    public boolean updateDriverUser(DriverUser driverUser){
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtModified(now);

        driverUserMapper.updateById(driverUser);
        return true;
    }

    /**
     * 删除司机信息
     * @param driverId 司机id
     * @return 响应结果
     */
    public boolean deleteDriverUser(Long driverId){
        driverUserMapper.deleteById(driverId);
        // TODO: 删除司机工作状态表（级联删除）或是直接将司机状态改为无效
        return true;
    }

    /**
     * 查询司机列表
     * @param driverUser 司机信息
     * @return 响应结果
     */
    public List<DriverUser> getDriverUser(DriverUser driverUser){
        return driverUserMapper.getDriverUserList(driverUser);
    }



    public ResponseResult<DriverUser> getDriverUserByPhone(String driverPhone){
        Map<String,Object> map = new HashMap<>();
        map.put("driver_phone", driverPhone);
        map.put("state", DriverCarConstants.DRIVER_STATE_VALID);
        // TODO: 为什么不用selectOne
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);
        if (driverUsers.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXITST.getCode(),CommonStatusEnum.DRIVER_NOT_EXITST.getValue());
        }
        DriverUser driverUser = driverUsers.get(0);
        return ResponseResult.success(driverUser);
    }

    public ResponseResult<DriverUser> getDriverUserById(DriverInfoVo driverInfoVo){
        DriverUser driverUser = driverUserMapper.selectById(driverInfoVo.getDriverId());
        return ResponseResult.success(driverUser);
    }

//    public ResponseResult<DriverUser> getDriverUserById(String driverId){
//        DriverUser driverUser = driverUserMapper.selectById(driverId);
//        return ResponseResult.success(driverUser);
//    }

    @Autowired
    DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    public ResponseResult<OrderDriverResponse> getAvailableDriver(Long carId){
        // 车辆和司机绑定关系查询
        QueryWrapper<DriverCarBindingRelationship> driverCarBindingRelationshipQueryWrapper = new QueryWrapper<>();
        driverCarBindingRelationshipQueryWrapper.eq("car_id",carId);
        driverCarBindingRelationshipQueryWrapper.eq("binding_state",DriverCarConstants.DRIVER_CAR_BIND);

        DriverCarBindingRelationship driverCarBindingRelationship = driverCarBindingRelationshipMapper.selectOne(driverCarBindingRelationshipQueryWrapper);
        Long driverId = driverCarBindingRelationship.getDriverId();
        // 司机工作状态的查询
        QueryWrapper<DriverUserWorkStatus> driverUserWorkStatusQueryWrapper = new QueryWrapper<>();
        driverUserWorkStatusQueryWrapper.eq("driver_id",driverId);
        driverUserWorkStatusQueryWrapper.eq("work_status",DriverCarConstants.DRIVER_WORK_STATUS_START);

        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatusMapper.selectOne(driverUserWorkStatusQueryWrapper);
        if (null == driverUserWorkStatus){
            return ResponseResult.fail(CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode(),CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getValue());
        }else {
            // 查询司机信息
            QueryWrapper<DriverUser> driverUserQueryWrapper = new QueryWrapper<>();
            driverUserQueryWrapper.eq("id",driverId);
            DriverUser driverUser = driverUserMapper.selectOne(driverUserQueryWrapper);
            // 查询车辆信息
            QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
            carQueryWrapper.eq("id",carId);
            Car car = carMapper.selectOne(carQueryWrapper);

            OrderDriverResponse orderDriverResponse = new OrderDriverResponse();
            orderDriverResponse.setCarId(carId);
            orderDriverResponse.setDriverId(driverId);
            orderDriverResponse.setDriverPhone(driverUser.getDriverPhone());

            orderDriverResponse.setLicenseId(driverUser.getLicenseId());
            orderDriverResponse.setVehicleNo(car.getVehicleNo());
            orderDriverResponse.setVehicleType(car.getVehicleType());

            return ResponseResult.success(orderDriverResponse);
        }
    }

}
