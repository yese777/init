package com.example.init.annotation;


import com.example.init.enums.BusinessType;

import java.lang.annotation.*;

/**
 * controller层自定义日志记录注解
 *
 * @author zhangqingfu
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    String module() default "";

    /**
     * 日志内容
     */
    String content() default "";

    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
