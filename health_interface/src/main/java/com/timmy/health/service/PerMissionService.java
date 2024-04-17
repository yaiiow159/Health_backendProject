package com.timmy.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.timmy.health.domain.Permission;
import com.timmy.health.domain.Setmeal;

import java.util.List;
import java.util.Set;

public interface PerMissionService extends IService<Permission> {
    Set<Permission> getPermissionsByRoleId(Integer roleId);

    IPage<Permission> getPages(Integer currenPage, Integer pageSize, Permission permission);

    Permission getPermissionById(Integer id);

    Integer addPermission(Permission permission);

    Integer editPermission(Permission permission);

    Integer deletePermission(Integer id);

    List<Permission> getAllPermission();
}
