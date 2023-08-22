package com.sdu.apiboss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LHP
 * @date 2023-07-12 20:50
 * @description
 */

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test api-boss";
    }

}
