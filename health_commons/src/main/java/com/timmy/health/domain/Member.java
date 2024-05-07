package com.timmy.health.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_member")
public class Member implements java.io.Serializable {
    @TableId(value = "id")
    private Integer id;
    @TableField(value = "fileNumber")
    private String fileNumber;
    @TableField(value = "name")
    private String name;
    @TableField(value = "sex")
    private String sex;
    @TableField(value = "idCard")
    private String idCard;
    @TableField(value = "phoneNumber")
    private String phoneNumber;
    @TableField(value = "regTime")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date regTime;
    @TableField(value = "password")
    private String password;
    @TableField(value = "email")
    private String email;
    @TableField(value = "birthday")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    @TableField(value = "remark")
    private String remark;
}
