package com.sdu.servicedriveruser.controller;

import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.servicedriveruser.mapper.DriverUserMapper;
import com.sdu.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LHP
 * @date 2023-07-12 18:11
 * @description
 */

@RestController
public class TestController {

    @Autowired
    private DriverUserService driverUserService;

    @GetMapping("/test")
    public String test(){
        return "test service-driver-user";
    }

    @GetMapping("/test-db")
    public ResponseResult testDb(){
        return driverUserService.testGetDriverUser();
    }

    // 测试mapper中的xml是否正常使用
    @Autowired
    DriverUserMapper driverUserMapper;

    @GetMapping("/test-xml")
    public int testXml(String cityCode){
        int i = driverUserMapper.selectDriverUserCountByCityCode(cityCode);
        return i;
    }
}
