package com.sdu.serviceorder.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author LHP
 * @date 2023-07-14 23:38
 * @description
 */

@Component
@Configuration
public class RedisConfig {

    private String potocol = "redis://";

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private String redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress(potocol+redisHost+":"+redisPort)
                .setDatabase(0)
                .setPassword(redisPassword);

        return Redisson.create(config);

    }
}
