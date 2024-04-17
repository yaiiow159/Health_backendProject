package com.timmy.health.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
public class Permission implements Serializable {
    private Integer id;
    private String name;
    private String keyword;
    private String description;
    private Set<Role> roles = new HashSet<>(0);
}
