package com.bird.controller;

import com.bird.domain.bo.LoginBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Author lipu
 * @Date 2021/9/29 19:54
 * @Description 登录接口
 */
@RestController
@RequestMapping("/security")
@Api(tags = "登录接口")
public class SecurityController {


    /**
     * @Author lipu
     * @Date 2021/9/29 20:03
     * @Description 用户登录 仅暴露给Swagger使用
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录")
    public void login(@RequestBody LoginBO loginBO) {

    }

    /**
     * @Author lipu
     * @Date 2021/10/4 11:44
     * @Description 用户退出 仅暴露给Swagger使用
     */
    @GetMapping("/logout")
    @ApiOperation(value = "用户退出")
    public void logout() {

    }
}
