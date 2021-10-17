package com.bird.security.filter;

import com.bird.domain.bo.LoginBO;
import com.bird.entity.Auth;
import com.bird.entity.CommonResult;
import com.bird.feign.IAuthFeign;
import com.bird.utils.JsonUtils;
import com.bird.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @Author lipu
 * @Date 2021/9/29 11:46
 * @Description 自定义登录逻辑
 */
@Slf4j
public class CustomerAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final IAuthFeign authFeign;
    private final RedisTemplate<String, Object> redisTemplate;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper OBJECTMAPPER = new ObjectMapper();

    public CustomerAuthenticationFilter(AuthenticationManager authenticationManager, IAuthFeign authFeign, RedisTemplate<String, Object> redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.authFeign = authFeign;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        //设置登录时的参数
        this.setUsernameParameter("account");
        this.setPasswordParameter("password");
        //设置登录URL
        this.setFilterProcessesUrl("/security/login");
    }

    /**
     * @Author lipu
     * @Date 2021/9/29 11:49
     * @Description 获取登录信息
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            //获取登录信息
            LoginBO loginBO = OBJECTMAPPER.readValue(request.getInputStream(), LoginBO.class);
            String account = loginBO.getAccount();
            String password = loginBO.getPassword();
            //将获取的用户信息封装并执行authenticationManager.authenticate(),Security会自动调用UserDetailsService
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(account, password);
            this.setDetails(request, authRequest);
            return authenticationManager.authenticate(authRequest);
        } catch (Exception e) {
            //该方法抛出异常会被认证异常处理器进行处理
            throw new RuntimeException();
        }
    }

    /**
     * @Author lipu
     * @Date 2021/9/29 11:49
     * @Description 登录成功处理
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        User user = (User) authResult.getPrincipal();
        //生成JWT令牌 并将用户信息存入到Redis中
        String jwt = JwtUtils.initJwt(user.getUsername());
        Auth auth = authFeign.getDetailsByAccount(user.getUsername());
        //与JWT令牌保持一致 8小时有效时间
        redisTemplate.opsForValue().set(JwtUtils.SECRET_KEY + ":" + auth.getAccount(), auth, 8, TimeUnit.HOURS);
        //将JWT存入请求头中
        response.addHeader(JwtUtils.AUTHORIZATION, jwt);
        String jsonResult = JsonUtils.entityToJson(CommonResult.success(jwt, "登录成功"));
        response.getWriter().write(jsonResult);
    }

    /**
     * @Author lipu
     * @Date 2021/9/29 11:49
     * @Description 登录失败处理
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String jsonResult = JsonUtils.entityToJson(CommonResult.unAuthorized());
        response.getWriter().write(jsonResult);
    }
}
