package com.bird.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author lipu
 * @Date 2021/9/29 19:58
 * @Description 登录接口参数
 */
@Data
@ApiModel(value = "登录接口参数")
public class LoginBO {

    @NotEmpty(message = "账号不能为空")
    @ApiModelProperty(value = "账号", required = true)
    private String account;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
