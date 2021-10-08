package com.bird.rpc;

import com.bird.entity.Auth;
import com.bird.service.IAuthService;
import com.bird.utils.JsonUtils;
import com.bird.utils.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2021/10/2 20:33
 * @Description
 */
@RestController
@RequestMapping("/rpc/auth")
public class AuthRpc {

    @Resource
    private IAuthService authService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @Author lipu
     * @Date 2021/9/27 16:24
     * @Description 根据id查询用户详情 用户信息+角色信息+权限信息
     */
    @GetMapping("/get-details-id")
    public Auth getDetailsById(@RequestParam("id") Long id) {
        return authService.getDetailsById(id);
    }

    /**
     * @Author lipu
     * @Date 2021/10/2 20:50
     * @Description 根据账号查询用户详情 用户信息+角色信息+权限信息
     */
    @GetMapping("/get-details-account")
    public Auth getDetailsByAccount(@RequestParam("account") String account) {
        return authService.getDetailsByAccount(account);
    }

    /**
     * @Author lipu
     * @Date 2021/10/2 22:07
     * @Description 获取登录用户信息
     */
    @GetMapping("/get-auth")
    public Auth getAuth(String jwt) {
        //jwt解析账户
        String account = JwtUtils.getSubject(jwt);
        //从redis查询用户的认证信息
        String json = (String) redisTemplate.opsForValue().get(JwtUtils.SECRET_KEY + ":" + account);
        return JsonUtils.jsonToObject(json, Auth.class);
    }

}
