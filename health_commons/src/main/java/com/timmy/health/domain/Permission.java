package com.timmy.health.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
public class Permission implements Serializable{
    private Integer id;
    private String name; // 權限名稱
    private String keyword; // 權限關鍵字，權限控制
    private String description; // 描述
    private Set<Role> roles = new HashSet<Role>(0);
}
