package com.sdu.servicepassengeruser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LHP
 * @date 2023-07-10 23:12
 * @description
 */

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test service-passenger-user";
    }

}
