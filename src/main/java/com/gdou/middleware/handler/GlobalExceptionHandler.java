package com.gdou.middleware.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import com.gdou.common.ResultCode;
import com.gdou.exception.AuthorizationException;
import com.gdou.exception.CustomException;
import com.gdou.exception.NotFoundException;
import com.gdou.pojo.vo.Result;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Raqtpie
 */
@Slf4j
@ResponseBody
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {
    /**
     * 异常处理方法
     * @param e 异常
     * @return 响应结果
     */
    @ExceptionHandler(CustomException.class)
    public Result exceptionHandler(CustomException e) {
        log.error(e.getMessage());
        return Result.builder().code(ResultCode.BAD_REQUEST.code).msg(e.getMessage()).build();
    }

    /**
     * 未授权异常处理方法
     * @param e 异常
     * @return 响应结果
     */
    @ExceptionHandler(AuthorizationException.class)
    public Result exceptionHandler(AuthorizationException e) {
        log.error(e.getMessage());
        return Result.builder().code(ResultCode.UNAUTHORIZED.code).msg(e.getMessage()).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public Result exceptionHandler(NotFoundException e) {
        log.error(e.getMessage());
        return Result.builder().code(ResultCode.NOT_FOUND.code).msg(e.getMessage()).build();
    }

    /**
     * 参数校验异常处理方法
     * @param e 异常
     * @return 响应结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result exceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return Result.builder()
                .code(ResultCode.BAD_REQUEST.code)
                .msg(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage())
                .build();
    }

    /**
     * 参数校验异常处理方法（Query参数）
     * @param e 异常
     * @return 响应结果
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result exceptionHandler(ConstraintViolationException e) {
        log.error(e.getMessage());
        return Result.builder().code(ResultCode.BAD_REQUEST.code).msg(e.getMessage().split(":")[1].trim()).build();
    }

    /**
     * 未登录异常处理方法
     * @param e 异常
     * @return 响应结果
     */
    @ExceptionHandler(NotLoginException.class)
    public Result exceptionHandler(NotLoginException e) {
        log.error(e.getMessage());
        return Result.builder().code(ResultCode.UNAUTHORIZED.code).msg("用户未登录或token无效").build();
    }

    /**
     * 角色没有权限异常处理方法
     */
    @ExceptionHandler(NotRoleException.class)
    public Result exceptionHandler(NotRoleException e) {
        log.error(e.getMessage());
        return Result.builder().code(ResultCode.BAD_REQUEST.code).msg("用户没有权限").build();
    }

    /**
     * 其他异常处理方法
     * @param e 异常
     * @return 响应结果
     */
//    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error(e.getMessage());
        return Result.builder().code(ResultCode.ERROR.code).msg("服务器内部错误").build();
    }
}
