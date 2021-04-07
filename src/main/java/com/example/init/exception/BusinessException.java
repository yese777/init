package com.example.init.exception;

import com.example.init.enums.BusinessExceptionMsgEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 自定义业务异常
 * 自定义业务异常提示信息建议在{@link BusinessExceptionMsgEnum}
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