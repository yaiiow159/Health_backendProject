package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_menu")
public class Menu implements Serializable {

    @TableId(value = "id")
    private Integer id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "linkUrl")
    private String linkUrl;
    @TableField(value = "path")
    private String path;
    @TableField(value = "priority")
    private Integer priority;
    @TableField(value = "description")
    private String description;
    @TableField(value = "icon")
    private String icon;
    @TableField(select = false,exist = false)
    private Set<Role> roles = new HashSet<>(0);
    @TableField(select = false,exist = false)
    private List<Menu> children = new ArrayList<>();
    @TableField(value = "parentMenuId")
    private Integer parentMenuId;
}
