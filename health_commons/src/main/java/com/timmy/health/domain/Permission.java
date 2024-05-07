package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_permission")
public class Permission implements Serializable {
    @TableId(value = "id")
    private Integer id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "keyword")
    private String keyword;
    @TableField(value = "description")
    private String description;
    @TableField(exist = false,select = false)
    private Set<Role> roles = new HashSet<>(0);
}
