package com.sdu.apiboss.service;

import com.sdu.apiboss.remote.ServiceEvaluateClient;
import com.sdu.internalcommon.dto.Evaluate;
import com.sdu.internalcommon.dto.EvaluateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LHP
 * @description
 */
@Service
public class EvaluateService {

    @Autowired
    ServiceEvaluateClient serviceEvaluateClient;

    public List<EvaluateVo> getEvaluateList(EvaluateVo evaluateVo){
        return serviceEvaluateClient.getEvaluateList(evaluateVo);
    }
}
