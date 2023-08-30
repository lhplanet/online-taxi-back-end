package com.sdu.servicedriveruser.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdu.internalcommon.constant.CommonStatusEnum;
import com.sdu.internalcommon.constant.DriverCarConstants;
import com.sdu.internalcommon.dto.DriverCarBindingRelationship;
import com.sdu.internalcommon.dto.DriverUser;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.result.ResultUtils;
import com.sdu.internalcommon.result.ResultVo;
import com.sdu.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import com.sdu.servicedriveruser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LHP
 * @description 司机车辆绑定关系服务类
 */
@Service
public class DriverCarBindingRelationshipService {

    @Autowired
    DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    @Autowired
    DriverUserMapper driverUserMapper;

    public ResultVo bind(DriverCarBindingRelationship driverCarBindingRelationship){
        // 判断，如果参数中的车辆和司机，已经做过绑定，则不允许再次绑定
        QueryWrapper<DriverCarBindingRelationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("binding_state",DriverCarConstants.DRIVER_CAR_BIND);

        Long l = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (l.intValue() > 0){
            return ResultUtils.error("该司机与该车辆已绑定！");
        }

        // 司机被绑定了
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("binding_state",DriverCarConstants.DRIVER_CAR_BIND);
        l = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (l.intValue() > 0){
            return ResultUtils.error("该司机已绑定其他车辆！");
        }

        // 车辆被绑定了
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("binding_state",DriverCarConstants.DRIVER_CAR_BIND);
        l = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if ((l.intValue() > 0)){
            return ResultUtils.error("该车辆已被其他司机绑定！");
        }

        // 之前有绑定记录，但是已经解绑了
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("binding_state",DriverCarConstants.DRIVER_CAR_UNBIND);
        l = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (l.intValue() > 0){
            DriverCarBindingRelationship driverCarBindingRelationship1 = driverCarBindingRelationshipMapper.selectOne(queryWrapper);
            driverCarBindingRelationship1.setBindingState(DriverCarConstants.DRIVER_CAR_BIND);
            driverCarBindingRelationship1.setBindingTime(LocalDateTime.now());
            driverCarBindingRelationshipMapper.updateById(driverCarBindingRelationship1);
            return ResultUtils.success("绑定成功！");
        }

        LocalDateTime now = LocalDateTime.now();
        driverCarBindingRelationship.setBindingTime(now);

        driverCarBindingRelationship.setBindingState(DriverCarConstants.DRIVER_CAR_BIND);

        driverCarBindingRelationshipMapper.insert(driverCarBindingRelationship);
        return ResultUtils.success("绑定成功！");

    }


    public ResultVo unbind(DriverCarBindingRelationship driverCarBindingRelationship){
        LocalDateTime now = LocalDateTime.now();

        Map<String,Object> map = new HashMap<>();
        map.put("driver_id",driverCarBindingRelationship.getDriverId());
        map.put("car_id",driverCarBindingRelationship.getCarId());
        map.put("binding_state", DriverCarConstants.DRIVER_CAR_BIND);

        List<DriverCarBindingRelationship> driverCarBindingRelationships = driverCarBindingRelationshipMapper.selectByMap(map);
        if (driverCarBindingRelationships.isEmpty()){
            return ResultUtils.error("该司机与该车辆未绑定！");
        }

        DriverCarBindingRelationship relationship = driverCarBindingRelationships.get(0);
        relationship.setBindingState(DriverCarConstants.DRIVER_CAR_UNBIND);
        relationship.setUnBindingTime(now);

        driverCarBindingRelationshipMapper.updateById(relationship);
        return ResultUtils.success("解绑成功！");

    }

    public ResponseResult<DriverCarBindingRelationship> getDriverCarRelationShipByDriverPhone(@RequestParam String driverPhone){
        QueryWrapper<DriverUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_phone",driverPhone);

        DriverUser driverUser = driverUserMapper.selectOne(queryWrapper);
        Long driverId = driverUser.getId();

        QueryWrapper<DriverCarBindingRelationship> driverCarBindingRelationshipQueryWrapper = new QueryWrapper<>();
        driverCarBindingRelationshipQueryWrapper.eq("driver_id",driverId);
        driverCarBindingRelationshipQueryWrapper.eq("binding_state",DriverCarConstants.DRIVER_CAR_BIND);

        DriverCarBindingRelationship driverCarBindingRelationship = driverCarBindingRelationshipMapper.selectOne(driverCarBindingRelationshipQueryWrapper);
        return ResponseResult.success(driverCarBindingRelationship);

    }

}
