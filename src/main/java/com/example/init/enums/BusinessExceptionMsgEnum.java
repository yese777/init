package com.example.init.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义业务异常提示信息枚举类
 * 其他更多的自定义业务异常提示信息在此定义
 */
@Getter
@AllArgsConstructor
public enum BusinessExceptionMsgEnum {

    /**
     * 其他异常
     */
    UNEXPECTED("系统发生异常，请联系管理员!"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST("该用户不存在"),

    /**
     * 等待超时
     */
    SERVICE_TIME_OUT("服务调用超时!"),

    ;

    /**
     * 提示消息
     */
    private final String message;
}