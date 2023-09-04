package com.sdu.apipassenger.remote;

import com.sdu.internalcommon.dto.Evaluate;
import com.sdu.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author LHP
 * @description
 */
@FeignClient(name = "service-order-4", url = "http://localhost:8089")
public interface ServiceEvaluateClient {

    @RequestMapping(method = RequestMethod.POST, value = "/evaluate/add")
    public ResponseResult add(@RequestBody Evaluate evaluate);

}
