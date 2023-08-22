package com.sdu.serviceorder.remote;

import com.sdu.internalcommon.request.PushRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author LHP
 * @description 调用远程service-sse-push服务
 */
@FeignClient("service-sse-push")
public interface ServiceSsePushClient {

    @PostMapping(value = "/push")
    public String push(@RequestBody PushRequest pushRequest);

}
