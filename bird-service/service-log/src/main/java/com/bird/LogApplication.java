package com.bird;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author lipu
 * @Date 2021/9/14 18:40
 * @Description 日志微服务启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableFeignClients
@MapperScan("com.bird.dao")
public class LogApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class);
    }
}
