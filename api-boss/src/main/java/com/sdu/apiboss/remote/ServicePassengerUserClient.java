package com.sdu.apiboss.remote;

import com.sdu.internalcommon.dto.PassengerUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author LHP
 * @description
 */
@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {


    @RequestMapping(method = RequestMethod.POST, value = "/user/add")
    public boolean addPassengerUser(@RequestBody PassengerUser passengerUser);

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public boolean updatePassengerUser(@RequestBody PassengerUser passengerUser);

    @RequestMapping(method = RequestMethod.DELETE, value = "/user")
    public boolean deletePassengerUser(@RequestBody Long passengerId);

    @RequestMapping(method = RequestMethod.POST, value = "/user/list")
    public List<PassengerUser> getPassengerUser(@RequestBody PassengerUser passengerUser);

    @RequestMapping(method = RequestMethod.GET, value = "/user/passenger-count")
    public int getPassengerCount();
}
