package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_role")
public class Role implements Serializable {
    @TableId(value = "id")
    private Integer id;
    @TableField(value = "name")
    private String name; // 角色名稱
    @TableField(value = "keyword")
    private String keyword; // 角色關键字，用于權限控制
    @TableField(value = "description")
    private String description; // 描述

    @TableField(exist = false)
    private Set<User> users = new HashSet<>(0);

    @TableField(exist = false)
    private Set<Permission> permissions = new HashSet<>(0);

    @TableField(exist = false)
    private LinkedHashSet<Menu> menus = new LinkedHashSet<>(0);
}
