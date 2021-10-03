package com.bird.rpc;

import com.bird.entity.Auth;
import com.bird.feign.IAuthFeign;
import com.bird.service.IAuthService;
import com.bird.utils.JsonUtils;
import com.bird.utils.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2021/10/2 20:33
 * @Description
 */
public class AuthRpc implements IAuthFeign {

    @Resource
    private IAuthService authService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @Author lipu
     * @Date 2021/9/27 16:24
     * @Description 根据id查询用户详情 用户信息+角色信息+权限信息
     */
    @Override
    public Auth getDetailsById(@RequestParam("id") Long id) {
        return authService.getDetailsById(id);
    }

    /**
     * @Author lipu
     * @Date 2021/10/2 20:50
     * @Description 根据账号查询用户详情 用户信息+角色信息+权限信息
     */
    @Override
    public Auth getDetailsByAccount(@RequestParam("account") String account) {
        return authService.getDetailsByAccount(account);
    }

    /**
     * @Author lipu
     * @Date 2021/10/2 22:07
     * @Description 获取登录用户信息
     */
    @Override
    public Auth getAuth(String jwt) {
        //jwt解析账户
        String account = JwtUtils.getSubject(jwt);
        //从redis查询用户的认证信息
        String json = (String) redisTemplate.opsForValue().get(JwtUtils.SECRET_KEY + ":" + account);
        return JsonUtils.jsonToObject(json, Auth.class);
    }
}
