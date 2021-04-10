package com.example.init.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 测试knife4j的忽略参数功能
 *
 * @author 张庆福
 * @date 2021/4/8
 */
@Data
public class B {
    @NotNull(message = "id不为空")
    private Integer id;
    @NotEmpty(message = "name不为空")
    private String name;
}
