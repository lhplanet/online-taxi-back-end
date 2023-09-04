package com.sdu.apiboss.remote;

import com.sdu.internalcommon.dto.Evaluate;
import com.sdu.internalcommon.dto.EvaluateVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author LHP
 * @description
 */
@FeignClient(name = "service-order-2", url = "http://localhost:8089")
public interface ServiceEvaluateClient {

    @RequestMapping(method = RequestMethod.POST, value = "/evaluate/list")
    public List<EvaluateVo> getEvaluateList(EvaluateVo evaluateVo);

}
