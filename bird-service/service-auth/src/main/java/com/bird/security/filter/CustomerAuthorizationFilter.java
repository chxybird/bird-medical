package com.bird.security.filter;

import com.bird.entity.Auth;
import com.bird.entity.Role;
import com.bird.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lipu
 * @Date 2021/10/2 21:37
 * @Description 自定义授权处理器
 */
@Slf4j
public class CustomerAuthorizationFilter extends BasicAuthenticationFilter {

    private final RedisTemplate<String, Object> redisTemplate;

    public CustomerAuthorizationFilter(AuthenticationManager authenticationManager, RedisTemplate<String, Object> redisTemplate) {
        super(authenticationManager);
        this.redisTemplate = redisTemplate;

    }

    /**
     * @author lipu
     * @date 2020/7/21 13:55
     * @Description JWT令牌校验授权服务
     */
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取令牌信息
        String jwt = request.getHeader(JwtUtils.AUTHORIZATION);
        if (jwt == null) {
            chain.doFilter(request, response);
            return;
        }
        //验证令牌信息
        JwtUtils.parseJwt(jwt);
        //对用户进行授权
        String account = JwtUtils.getSubject(jwt);
        //从redis查询用户的认证信息
        Auth auth = (Auth) redisTemplate.opsForValue().get(JwtUtils.SECRET_KEY + ":" + account);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (auth != null) {
            List<Role> roleList = auth.getRoleList();
            authorityList = roleList.stream().map(item -> {
                return (GrantedAuthority) new SimpleGrantedAuthority(item.getRoleCode());
            }).collect(Collectors.toList());
        }
        //构造Security对象
        UsernamePasswordAuthenticationToken anthRequest = new UsernamePasswordAuthenticationToken(account, null, authorityList);
        //将对象交给Security来处理
        SecurityContextHolder.getContext().setAuthentication(anthRequest);
        chain.doFilter(request, response);
    }


}
