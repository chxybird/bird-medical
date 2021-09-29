package com.bird.entity;

import com.bird.constant.ResultStatusConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author lipu
 * @Date 2021/9/25 17:16
 * @Description 通用结果集
 */
@Data
@ApiModel("通用结果集")
public class CommonResult<T> implements Serializable {

    @ApiModelProperty(value = "状态码", example = "404 200")
    private Integer status;
    @ApiModelProperty(value = "数据")
    private T data;
    @ApiModelProperty(value = "提示信息", example = "成功")
    private String message;


    public static <T> CommonResult<T> success(T data, String message) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setData(data);
        commonResult.setStatus(ResultStatusConst.SUCCESS);
        commonResult.setMessage(message);
        return commonResult;
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setData(data);
        commonResult.setStatus(ResultStatusConst.SUCCESS);
        commonResult.setMessage("请求成功");
        return commonResult;
    }

    public static <T> CommonResult<T> error(T data) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setData(data);
        commonResult.setStatus(ResultStatusConst.ERROR);
        commonResult.setMessage("发生异常");
        return commonResult;
    }

    public static <T> CommonResult<T> unAuthorized() {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setStatus(ResultStatusConst.UNAUTHORIZED);
        commonResult.setMessage("认证失败");
        return commonResult;
    }

}
