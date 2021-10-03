package com.bird.feign;

import com.bird.entity.Auth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lipu
 * @Date 2021/10/2 20:24
 * @Description
 */
@RestController
@FeignClient("service-auth")
@RequestMapping("/rpc/auth")
public interface IAuthFeign {

    /**
     * 根据id查询用户详情 用户信息+角色信息+权限信息
     *
     * @param id 用户id
     * @return 用户对象信息(用户信息 + 角色信息 + 权限信息)
     */
    @GetMapping("/get-details-id")
    Auth getDetailsById(@RequestParam("id") Long id);

    /**
     * 根据账号查询用户详情 用户信息+角色信息+权限信息
     *
     * @param account 用户账号
     * @return 用户对象信息(用户信息 + 角色信息 + 权限信息)
     */
    @GetMapping("/get-details-account")
    Auth getDetailsByAccount(@RequestParam("account") String account);

    /**
     * 获取登录用户的信息
     *
     * @param jwt 令牌
     * @return 登录用户信息
     */
    @GetMapping("get-auth")
    Auth getAuth(@RequestParam("jwt") String jwt);


}
