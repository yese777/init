package com.example.init.config;


import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.init.enums.BusinessExceptionMsgEnum;
import com.example.init.enums.ResultCodeEnum;
import com.example.init.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.StringJoiner;

/**
 * @author 张庆福
 * @description 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义业务异常,状态是200,由前端自行处理
     * 使用自定义业务异常时,直接在代码中throw new BusinessException();
     * 自定义业务异常提示信息建议统一在{@link BusinessExceptionMsgEnum}枚举中定义
     * @param e {@link BusinessException}
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public R<?> handleBusinessException(BusinessException e, HttpServletRequest request) {
        String url = request.getRequestURI();
        log.error("url:【{}】自定义业务异常", url, e);

        return R.failed(new IErrorCode() {
            @Override
            public long getCode() {
                return ResultCodeEnum.BUSINESS_ERROR.getCode();
            }

            @Override
            public String getMsg() {
                return e.getMessage();
            }
        });
    }

    /**
     * post方式提交json数据,参数校验失败后
     * @param e MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public R<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String url = request.getRequestURI();
        // 将所有的错误提示使用";"拼接起来并返回
        StringJoiner sj = new StringJoiner(";");
        e.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));
        log.error("url:[{}]post请求参数校验失败:{}", url, sj.toString());
        return R.restResult(sj.toString(), ResultCodeEnum.PARAMETER_VALIDATE_FAIL);
    }

    /**
     * get方式提交参数,参数校验失败后
     * @param e ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public R<?> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        String url = request.getRequestURI();
        StringJoiner sj = new StringJoiner(";");
        e.getConstraintViolations().forEach(x -> sj.add(x.getMessage()));
        log.error("url:[{}]get请求参数校验失败:{}", url, sj.toString());
        return R.restResult(sj.toString(), ResultCodeEnum.PARAMETER_VALIDATE_FAIL);
    }

    /**
     * 空指针异常
     * @param e NullPointerException
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public R<?> handleNullPointerException(NullPointerException e, HttpServletRequest request) {
        String url = request.getRequestURI();
        log.error("url:【{}】空指针异常", url, e);
        return R.failed(ResultCodeEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 缺少服务器请求参数异常
     * @param e MissingServletRequestParameterException
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public R<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
        String url = request.getRequestURI();
        log.error("url:【{}】缺少必要的请求参数", url, e);
        return R.failed(ResultCodeEnum.PARAMETER_BLANK);
    }

    /**
     * Json序列化失败
     * @param e HttpMessageNotReadableException
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public R<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        String url = request.getRequestURI();
        log.error("url:【{}】Json序列化失败", url, e);
        return R.failed(ResultCodeEnum.BAD_REQUEST);
    }

    /**
     * 接口不存在
     * 此异常未能成功捕获
     * @param e NoHandlerFoundException
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public R<?> handlerNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request) {
        String url = request.getRequestURI();
        log.error("url:【{}】资源不存在", url, e);
        return R.failed(ResultCodeEnum.NOT_FOUND);
    }

    /**
     * 不支持的Method
     * @param e HttpRequestMethodNotSupportedException
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public R<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String url = request.getRequestURI();
        log.error("url:【{}】不支持:【{}】请求类型", url, e.getMethod(), e);
        return R.failed(ResultCodeEnum.METHOD_NOT_ALLOWED);
    }

    /**
     * 其他系统预期以外的异常都在此处理
     * @param e Exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public R<?> handleUnexpectedException(Exception e, HttpServletRequest request) {
        String url = request.getRequestURI();
        log.error("url:【{}】系统发生异常", url, e);
        return R.failed(ResultCodeEnum.INTERNAL_SERVER_ERROR);
    }

}