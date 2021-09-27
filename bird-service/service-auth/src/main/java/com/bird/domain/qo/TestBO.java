package com.bird.domain.qo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author lipu
 * @Date 2021/9/27 18:19
 * @Description
 */
@Data
public class TestBO {
    @NotNull(message = "id不能为空")
    private Long id;

    private String name;
}
