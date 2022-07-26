package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.*;

@Data
public class Menu implements Serializable{

    @TableId
    @NotNull
    private Integer id;
    private String name;
    private String linkUrl;
    private String path;
    private Integer priority;
    private String description;
    private String icon;
    private Set<Role> roles = new HashSet<>(0);
    private List<Menu> children = new ArrayList<>();
    private Integer parentMenuId;
}
