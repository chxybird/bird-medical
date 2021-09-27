package com.bird;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author lipu
 * @Date 2021/9/21 19:57
 * @Description 认证微服务启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.bird.dao")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
