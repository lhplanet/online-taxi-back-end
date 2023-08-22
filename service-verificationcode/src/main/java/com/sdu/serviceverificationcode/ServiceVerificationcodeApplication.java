package com.sdu.serviceverificationcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 开启服务发现客户端
public class ServiceVerificationcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVerificationcodeApplication.class);
    }

}
