package com.bird.controller;


import com.bird.entity.Auth;
import com.bird.entity.CommonResult;
import com.bird.service.IAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李璞
 * @since 2021-09-27
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "用户接口")
@Validated
@Slf4j
public class AuthController {
    @Resource
    private IAuthService authService;

    /**
     * @Author lipu
     * @Date 2021/9/27 14:15
     * @Description 根据id查询用户信息
     */
    @GetMapping("/get-id")
    @ApiOperation("根据id查询用户信息")
    public CommonResult<Auth> getById(@ApiParam(name = "用户id") @NotNull(message = "必须填入用户id") Long id) {
        Auth auth = authService.getById(id);
        return CommonResult.success(auth);
    }

    /**
     * @Author lipu
     * @Date 2021/9/27 16:24
     * @Description 根据id查询用户详情 用户信息+角色信息+权限信息
     */
    @GetMapping("/get-details-id")
    @ApiOperation("根据id查询用户详情信息 用户信息+角色信息+权限信息")
    public CommonResult<Auth> getDetailsById(@NotNull(message = "必须填入用户id") @ApiParam(name = "用户id") Long id) {
        Auth auth = authService.getDetailsById(id);
        return CommonResult.success(auth);
    }

    /**
     * @Author lipu
     * @Date 2021/9/27 16:24
     * @Description 根据账号查询用户详情 用户信息+角色信息+权限信息
     */
    @GetMapping("/get-details-account")
    @ApiOperation("根据账号查询用户详情信息 用户信息+角色信息+权限信息")
    public CommonResult<Auth> getDetailsByAccount(@NotNull(message = "必须填入用户id") @ApiParam(name = "用户账号") String account) {
        Auth auth = authService.getDetailsByAccount(account);
        return CommonResult.success(auth);
    }

}

