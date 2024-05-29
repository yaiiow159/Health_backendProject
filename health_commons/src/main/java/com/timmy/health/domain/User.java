package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_user")
public class User implements Serializable {
    @TableId
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "birthday")
    private Date birthday;
    @TableField(value = "gender")
    private String gender;
    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;
    @TableField(value = "remark")
    private String remark;
    @TableField(value = "station")
    private String station;
    @TableField(value = "telephone")
    private String telephone;

    @TableField(exist = false)
    private Set<Role> roles = new HashSet<>(0);//對應角色
}
