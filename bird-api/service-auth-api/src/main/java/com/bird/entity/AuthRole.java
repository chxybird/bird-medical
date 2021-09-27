package com.bird.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 李璞
 * @since 2021-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_auth_role")
@ApiModel(value = "AuthRole对象", description = "")
public class AuthRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键自增长")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "外键 用户id")
    private Long authId;

    @ApiModelProperty(value = "外键 角色id")
    private Long roleId;


}
