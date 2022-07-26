package com.timmy.health.entity;

import lombok.*;
import java.io.Serializable;

/**
 * 封装返回结果
 */


@Setter
@Getter
@NoArgsConstructor
public class Result implements Serializable{

    private boolean flag;
    private String message;
    private Object data;

    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public Result(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

}
