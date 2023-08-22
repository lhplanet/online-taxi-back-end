package com.sdu.servicemap.controller;

import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.servicemap.service.ServiceFromMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LHP
 * @date 2023-07-13 22:26
 * @description 服务管理控制器
 */

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceFromMapService serviceFromMapService;

    /**
     * 创建服务
     * @param name
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(String name){
        return serviceFromMapService.add(name);
    }

}
