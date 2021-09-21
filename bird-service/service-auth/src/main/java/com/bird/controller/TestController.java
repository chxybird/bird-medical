package com.bird.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author lipu
 * @Date 2021/9/21 21:21
 * @Description
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * @Author lipu
     * @Date 2021/9/21 21:22
     * @Description 网关测试
     */
    @GetMapping("/get-info")
    public Mono<String> getInfo() {
        return Mono.just("网关测试成功");
    }
}
