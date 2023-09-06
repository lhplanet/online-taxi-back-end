package com.sdu.servicepassengeruser.service;

import com.sdu.internalcommon.constant.CommonStatusEnum;
import com.sdu.internalcommon.dto.PassengerUser;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LHP
 * @description 用户服务类
 */
@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;


    /**
     * 添加乘客
     * @param passengerUser 乘客信息
     * @return 响应结果
     */
    public boolean addPassengerUser(PassengerUser passengerUser){

        LocalDateTime now = LocalDateTime.now();
        passengerUser.setGmtCreate(now);
        passengerUser.setGmtModified(now);

        // TODO: 是否要加上判断创建成功与否
        passengerUserMapper.insert(passengerUser);

        return true;
    }

    /**
     * 编辑乘客信息
     * @param passengerUser 乘客信息
     * @return 响应结果
     */
    public boolean updatePassengerUser(PassengerUser passengerUser){
        LocalDateTime now = LocalDateTime.now();
        passengerUser.setGmtModified(now);

        passengerUserMapper.updateById(passengerUser);
        return true;
    }

    /**
     * 删除乘客信息
     * @param passengerId 乘客id
     * @return 响应结果
     */
    public boolean deletePassengerUser(Long passengerId){
        passengerUserMapper.deleteById(passengerId);
        // TODO: 删除乘客工作状态表（级联删除）或是直接将乘客状态改为无效
        return true;
    }

    /**
     * 查询乘客列表
     * @param passengerUser 乘客信息
     * @return 响应结果
     */
    public List<PassengerUser> getPassengerUser(PassengerUser passengerUser){
        return passengerUserMapper.getPassengerUserList(passengerUser);
    }



    public ResponseResult loginOrRegister(String passengerPhone) {

        // 根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);

        // 判断用户信息是否存在
        if (passengerUsers.size()==0) {
            // 如果不存在，插入用户信息（注册用户）
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("张三");
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setPassengerGender((byte) 1);
            // 0-有效
            passengerUser.setState((byte) 0);
            LocalDateTime now = LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);
            passengerUserMapper.insert(passengerUser);
        }

        return ResponseResult.success("");
    }

    /**
     * 根据手机号查询用户信息
     * @param passengerPhone
     * @return
     */
    public ResponseResult getUserByPhone(String passengerPhone) {
        // 根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        if (passengerUsers.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXISTS.getCode(), CommonStatusEnum.USER_NOT_EXISTS.getValue());
        } else {
            PassengerUser passengerUser = passengerUsers.get(0);
            return ResponseResult.success(passengerUser);
        }
    }

    public int getPassengerCount() {
        return passengerUserMapper.getPassengerCount();
    }

}
