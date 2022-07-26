package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;


@Data
public class Member implements Serializable{

    @TableId
    @NotNull
    private Integer id;
    private String fileNumber;
    private String name;
    private String sex;
    private String idCard;
    private String phoneNumber;
    private Date regTime;
    private String password;
    private String email;
    private Date birthday;
    private String remark;
}
