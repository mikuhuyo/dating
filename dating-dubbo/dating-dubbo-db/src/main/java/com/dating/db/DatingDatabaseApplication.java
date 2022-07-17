package com.dating.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
public class DatingDatabaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatingDatabaseApplication.class, args);
    }
}
