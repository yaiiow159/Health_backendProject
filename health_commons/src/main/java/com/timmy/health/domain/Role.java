package com.timmy.health.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


@Data
public class Role implements Serializable {
    private Integer id;
    private String name; // 角色名稱
    private String keyword; // 角色關键字，用于權限控制
    private String description; // 描述
    private Set<User> users = new HashSet<>(0);
    private Set<Permission> permissions = new HashSet<>(0);
    private LinkedHashSet<Menu> menus = new LinkedHashSet<>(0);
}
