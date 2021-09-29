package com.bird.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2021/9/29 19:58
 * @Description 登录接口参数
 */
@Data
@ApiModel(value = "登录接口参数")
public class LoginBO {

    @ApiModelProperty(value = "账号", required = true)
    private String account;

    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
