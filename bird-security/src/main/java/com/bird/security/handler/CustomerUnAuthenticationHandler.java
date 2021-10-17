package com.bird.security.handler;

import com.bird.entity.CommonResult;
import com.bird.utils.JsonUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author lipu
 * @Date 2021/9/29 12:20
 * @Description 自定义认证异常处理
 */
public class CustomerUnAuthenticationHandler implements AuthenticationEntryPoint {

    /**
     * @Author lipu
     * @Date 2021/9/29 12:22
     * @Description 认证异常处理逻辑
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String jsonResult = JsonUtils.entityToJson(CommonResult.unAuthorized());
        response.getWriter().write(jsonResult);
    }
}
