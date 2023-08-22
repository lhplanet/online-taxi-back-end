package com.sdu.servicemap.controller;

import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.servicemap.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LHP
 * @description 轨迹控制类
 */
@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    TrackService trackService;

    /**
     * 添加轨迹
     * @param tid 终端id
     * @return 响应结果
     */
    @PostMapping("/add")
    public ResponseResult add(String tid){

        return trackService.add(tid);
    }
}
