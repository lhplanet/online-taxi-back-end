package com.sdu.apipassenger.remote;

import com.sdu.internalcommon.request.PushRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author LHP
 * @description
 */
@FeignClient("service-sse-push")
public interface ServicePushClient {

    @RequestMapping(method = RequestMethod.POST,value = "/push")
    public String pdBegin(@RequestBody PushRequest pushRequest);

}
