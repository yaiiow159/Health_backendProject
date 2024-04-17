package com.timmy.health.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timmy.health.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    Set<Permission> getPermissionByRoleId(Integer roleId);

    IPage<Permission> getPages(Page<Permission> page, Permission permission);

    Integer save(Permission permission);

    Integer edit(Permission permission);

    Permission findById(Integer id);
}
