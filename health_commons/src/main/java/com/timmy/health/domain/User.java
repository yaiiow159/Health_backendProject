package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class User implements Serializable {
    @TableId
    @NotNull
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String gender;
    private String username;
    private String password;
    private String remark;
    private String station;
    private String telephone;
    private Set<Role> roles = new HashSet<>(0);//對應角色
}
