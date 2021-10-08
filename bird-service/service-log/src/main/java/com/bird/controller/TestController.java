package com.bird.controller;

import com.bird.entity.Auth;
import com.bird.entity.CommonResult;
import com.bird.feign.IAuthFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2021/10/8 15:22
 * @Description
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试")
public class TestController {

    @Resource
    private IAuthFeign authFeign;

    @ApiOperation("测试")
    @GetMapping("/test")
    public CommonResult<Auth> test() {
        Auth auth = authFeign.getDetailsById(1L);
        return CommonResult.success(auth);
    }
}
