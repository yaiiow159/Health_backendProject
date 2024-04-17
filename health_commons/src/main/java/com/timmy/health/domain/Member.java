package com.timmy.health.domain;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class Member implements java.io.Serializable {
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
