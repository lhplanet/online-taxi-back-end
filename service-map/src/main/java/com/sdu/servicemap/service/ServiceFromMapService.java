package com.sdu.servicemap.service;

import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.servicemap.remote.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LHP
 * @date 2023-07-13 22:26
 * @description
 */

@Service
public class ServiceFromMapService {

    @Autowired
    private ServiceClient serviceClient;


    /**
     * 创建服务
     * @param name
     * @return
     */
    public ResponseResult add(String name){
        return serviceClient.add(name);
    }

}
