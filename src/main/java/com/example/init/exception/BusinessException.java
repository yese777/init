package com.example.init.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 自定义业务异常
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    /**
     * 异常提示信息
     */
    private String message;

}