package com.example.init.vo;

import lombok.Data;

/**
 * 测试knife4j的忽略参数功能
 *
 * @author 张庆福
 * @date 2021/4/8
 */
@Data
public class A {
    private Integer id;
    private String name;
    private B b;
}
