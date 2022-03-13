package com.timmy.health.utils;


import com.timmy.health.constant.MessageConstant;
import com.timmy.health.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(Exception.class)
    public Result getAdviceResult(Exception e){
        e.printStackTrace();
        return new Result(false, "服務錯誤，請稍等");
    }
}
