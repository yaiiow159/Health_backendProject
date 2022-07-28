package com.timmy.health.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timmy.health.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    Set<Permission> getPermissionByRoleId(Integer roleId);
}
