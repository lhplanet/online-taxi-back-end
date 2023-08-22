package com.sdu.servicemap.service;

import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.response.TerminalResponse;
import com.sdu.internalcommon.response.TrsearchResponse;
import com.sdu.servicemap.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LHP
 * @description 终端服务类
 */
@Service
public class TerminalService {

    @Autowired
    TerminalClient terminalClient;

    public ResponseResult<TerminalResponse> add(String name, String desc) {

        return terminalClient.add(name, desc);
    }


    public ResponseResult<List<TerminalResponse>> aroundsearch(String center, Integer radius) {

        return terminalClient.aroundsearch(center, radius);
    }

    public ResponseResult<TrsearchResponse> trsearch(String tid, Long starttime, Long endtime) {

        return terminalClient.trsearch(tid, starttime, endtime);
    }

}
