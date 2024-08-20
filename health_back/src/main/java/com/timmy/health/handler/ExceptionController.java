package com.timmy.health.handler;

import com.timmy.health.constant.ErrorMessageConstant;
import com.timmy.health.entity.Result;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author timmy
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        return new Result(false, "系統異常 錯誤原因為: " + e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public Result authenticationExceptionHandler(Exception e) {
        return new Result(false, ErrorMessageConstant.USER_LOGIN_AUTH_FAILURE);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result accessDeniedExceptionHandler(Exception e) {
        return new Result(false, ErrorMessageConstant.ACCESS_DENIED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationExceptionHandler(Exception e) {
        return new Result(false, ErrorMessageConstant.CONSTRAINT_VIOLATION);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result dataIntegrityViolationExceptionHandler(Exception e) {
        return new Result(false, ErrorMessageConstant.DataIntegrityViolation);
    }

}
