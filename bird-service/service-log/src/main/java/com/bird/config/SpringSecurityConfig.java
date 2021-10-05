package com.bird.config;

import com.bird.feign.IAuthFeign;
import com.bird.security.AuthServiceImpl;
import com.bird.security.filter.CustomerAuthenticationFilter;
import com.bird.security.filter.CustomerAuthorizationFilter;
import com.bird.security.handler.CustomerLogoutHandler;
import com.bird.security.handler.CustomerLogoutSuccessHandler;
import com.bird.security.handler.CustomerUnAuthenticationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2021/9/28 14:54
 * @Description SpringSecurity配置类
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private IAuthFeign authFeign;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private AuthServiceImpl authService;

    /**
     * @Author lipu
     * @Date 2021/9/28 14:58
     * @Description 加密策略配置 BCrypt
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * @Author lipu
     * @Date 2021/4/16 10:22
     * @Description 配置用户的来源(数据库)以及加密方式
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService)
                .passwordEncoder(bCryptPasswordEncoder());
    }


    /**
     * @Author lipu
     * @Date 2021/4/16 11:14
     * @Description SpringSecurity规则配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭CSRF防护
        http.csrf().disable();

        //异常处理
        http.exceptionHandling().authenticationEntryPoint(new CustomerUnAuthenticationHandler());

        //设置认证规则 -- 所有接口都需要认证
        http.authorizeRequests().anyRequest().authenticated();

        //设置自定义过滤器
        http
                .addFilter(new CustomerAuthenticationFilter(super.authenticationManager(), authFeign, redisTemplate))
                .addFilter(new CustomerAuthorizationFilter(super.authenticationManager(), redisTemplate));

        //自定义退出
        http.logout()
                .logoutUrl("/security/logout")
                .addLogoutHandler(new CustomerLogoutHandler(redisTemplate))
                .logoutSuccessHandler(new CustomerLogoutSuccessHandler());

        //禁用Session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    /**
     * @Author lipu
     * @Date 2021/9/29 12:48
     * @Description 配置放行的资源
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                //放行Swagger与Knife4j资源
                .ignoring().antMatchers(
                "/v2/**"
                , "/swagger/**"
                , "/webjars/**"
                , "/swagger-resources/**"
                , "/doc.html"
                , "/swagger-ui.html"
        );
    }
}
