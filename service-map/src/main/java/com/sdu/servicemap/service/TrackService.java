package com.sdu.servicemap.service;

import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.response.TrackResponse;
import com.sdu.servicemap.remote.TrackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LHP
 * @description 轨迹服务类
 */
@Service
public class TrackService {

    @Autowired
    TrackClient trackClient;

    /**
     * 添加轨迹
     * @param tid 终端id
     * @return 响应结果
     */
    public ResponseResult<TrackResponse> add(String tid){
        return trackClient.add(tid);
    }
}
