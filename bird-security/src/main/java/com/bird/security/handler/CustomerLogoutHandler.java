package com.bird.security.handler;

import com.bird.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author lipu
 * @Date 2021/9/29 11:41
 * @Description 自定义退出处理器
 */
@Slf4j
public class CustomerLogoutHandler implements LogoutHandler {

    private final RedisTemplate<String, Object> redisTemplate;


    public CustomerLogoutHandler(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String jwt = request.getHeader(JwtUtils.AUTHORIZATION);
        String account = JwtUtils.getSubject(jwt);
        //从Redis中移除用户信息
        redisTemplate.delete(JwtUtils.SECRET_KEY + ":" + account);
    }


}
