package com.bird.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author lipu
 * @Date 2021/9/22 12:09
 * @Description swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                //配置api的描述信息
                .apiInfo(apiInfo())
                //开启swagger false则不能在浏览器中访问
                .enable(true)
                //设置扫描的包
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bird.controller"))
                //设置过滤信息
                .paths(PathSelectors.any())
                .build();
    }
    
    /**
     * @Author lipu
     * @Date 2021/9/30 14:04
     * @Description 配置ApiInfo信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("技术调研--小鸟程序员")
                .contact(new Contact("小鸟程序员", "井底之蛙团队", "2450107493@qq.com"))
                .description("制作人:李璞")
                .termsOfServiceUrl("http://swagger.io/")
                .version("2.0")
                .build();
    }
}
