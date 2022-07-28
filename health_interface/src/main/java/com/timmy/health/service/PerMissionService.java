package com.timmy.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timmy.health.domain.Permission;

import java.util.Set;

public interface PerMissionService extends IService<Permission> {
    Set<Permission> getPermissionsByRoleId(Integer roleId);
}
