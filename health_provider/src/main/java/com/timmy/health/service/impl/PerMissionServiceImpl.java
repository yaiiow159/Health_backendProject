package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timmy.health.domain.Permission;
import com.timmy.health.mapper.PermissionMapper;
import com.timmy.health.service.PerMissionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@DubboService(interfaceClass = PerMissionService.class)
public class PerMissionServiceImpl extends ServiceImpl<PermissionMapper,Permission> implements PerMissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<Permission> getPermissionsByRoleId(Integer roleId) {
        return permissionMapper.getPermissionByRoleId(roleId);
    }

    @Override
    public IPage<Permission> getPages(Integer currenPage, Integer pageSize, Permission permission) {
        Page<Permission> page = new Page<>(currenPage, pageSize);
        return permissionMapper.getPages(page, permission);
    }

    @Override
    public Permission getPermissionById(Integer id) {
        return permissionMapper.findById(id);
    }

    @Override
    public Integer addPermission(Permission permission) {
        return permissionMapper.save(permission);
    }

    @Override
    public Integer editPermission(Permission permission) {
        return permissionMapper.edit(permission);
    }

    @Override
    public Integer deletePermission(Integer id) {
        return permissionMapper.deleteById(id);
    }

    @Override
    public List<Permission> getAllPermission() {
        return permissionMapper.selectList(null);
    }
}

