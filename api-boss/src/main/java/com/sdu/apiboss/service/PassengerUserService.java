package com.sdu.apiboss.service;

import com.sdu.apiboss.remote.ServicePassengerUserClient;
import com.sdu.internalcommon.dto.PassengerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LHP
 * @description
 */
@Service
public class PassengerUserService {

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    public boolean addPassengerUser(PassengerUser passengerUser){
        return servicePassengerUserClient.addPassengerUser(passengerUser);
    }

    public boolean updatePassengerUser(PassengerUser passengerUser){
        return servicePassengerUserClient.updatePassengerUser(passengerUser);
    }

    public boolean deletePassengerUser(Long passengerId){
        return servicePassengerUserClient.deletePassengerUser(passengerId);
    }

    public List<PassengerUser> getPassengerUser(PassengerUser passengerUser){
        return servicePassengerUserClient.getPassengerUser(passengerUser);
    }

}
