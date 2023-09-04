package com.sdu.apipassenger.service;

import com.sdu.apipassenger.remote.ServiceEvaluateClient;
import com.sdu.internalcommon.dto.Evaluate;
import com.sdu.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LHP
 * @description
 */
@Service
public class EvaluateService {

    @Autowired
    ServiceEvaluateClient serviceEvaluateClient;

    public ResponseResult add(Evaluate evaluate){
        return serviceEvaluateClient.add(evaluate);
    }

}
