package com.bird.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @Author lipu
 * @Date 2021/9/27 16:56
 * @Description JSR303校验配置类
 */
@Configuration
public class ValidConfig {

    /**
     * @Author lipu
     * @Date 2021/9/27 16:56
     * @Description 开启快速失败模式
     */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

}
