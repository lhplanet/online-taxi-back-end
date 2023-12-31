package com.sdu.apiboss.remote;

import com.sdu.internalcommon.dto.*;
import com.sdu.internalcommon.result.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author LHP
 * @description 远程调用service-driver-user服务
 */
@FeignClient(name = "service-driver-user-1", url = "http://localhost:8086")
public interface ServiceDriverUserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public boolean addDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public boolean updateDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.DELETE, value = "/user")
    public boolean deleteDriverUser(@RequestBody Long driverId);

    @RequestMapping(method = RequestMethod.POST, value = "/user/list")
    public List<DriverUser> getDriverUser(@RequestBody DriverUser driverUser);



    @RequestMapping(method = RequestMethod.POST,value = "/driver-car-binding-relationship/bind")
    public ResultVo bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);

    @RequestMapping(method = RequestMethod.POST,value = "/driver-car-binding-relationship/unbind")
    public ResultVo unbind(DriverCarBindingRelationship driverCarBindingRelationship);

    @RequestMapping(method = RequestMethod.GET, value = "/user/driver-count")
    public int getDriverCount();
}
