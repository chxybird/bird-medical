package com.bird.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lipu
 * @Date 2021/9/27 13:10
 * @Description
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * @Author lipu
     * @Date 2021/4/14 10:00
     * @Description MP分页插件配置
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
