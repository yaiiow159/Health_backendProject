package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timmy.health.domain.Permission;
import com.timmy.health.mapper.PermissionMapper;
import com.timmy.health.service.PerMissionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Transactional
@DubboService(interfaceClass = PerMissionService.class)
@Service
public class PerMissionServiceImpl extends ServiceImpl<PermissionMapper,Permission> implements PerMissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<Permission> getPermissionsByRoleId(Integer roleId) {
        return permissionMapper.getPermissionByRoleId(roleId);
    }
}

