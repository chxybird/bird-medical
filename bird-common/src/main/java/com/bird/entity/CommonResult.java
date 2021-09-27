package com.bird.entity;

import com.bird.constant.ResultStatusConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2021/9/25 17:16
 * @Description 通用结果集
 */
@Data
@ApiModel("通用结果集")
public class CommonResult<T> {

    @ApiModelProperty(value = "状态码", example = "404 200")
    private Integer status;
    @ApiModelProperty(value = "数据")
    private T data;
    @ApiModelProperty(value = "提示信息", example = "成功")
    private String message;


    public CommonResult<T> success(T data, String message) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setData(data);
        commonResult.setStatus(ResultStatusConst.SUCCESS);
        commonResult.setMessage(message);
        return commonResult;
    }

    public CommonResult<T> success(T data) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setData(data);
        commonResult.setStatus(ResultStatusConst.SUCCESS);
        commonResult.setMessage("请求成功");
        return commonResult;
    }
}