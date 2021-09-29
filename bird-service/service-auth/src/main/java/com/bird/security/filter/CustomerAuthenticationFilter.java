package com.bird.security.filter;

import com.bird.domain.bo.LoginBO;
import com.bird.entity.CommonResult;
import com.bird.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author lipu
 * @Date 2021/9/29 11:46
 * @Description 自定义登录逻辑
 */
public class CustomerAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final ObjectMapper OBJECTMAPPER = new ObjectMapper();

    private final AuthenticationManager authenticationManager;

    public CustomerAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
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
        //为用户生成Token
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String jsonResult = JsonUtils.entityToJson(CommonResult.success(UUID.randomUUID().toString(), "登录成功"));
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
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String jsonResult = JsonUtils.entityToJson(CommonResult.unAuthorized());
        response.getWriter().write(jsonResult);
    }
}
