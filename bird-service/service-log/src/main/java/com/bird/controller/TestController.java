package com.bird.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lipu
 * @Date 2021/10/2 13:33
 * @Description
 */
@RestController
@Api(tags = "测试接口")
public class TestController {

    @GetMapping("/test")
    @ApiOperation("测试")
    public String test() {
        return "success";
    }
}
