package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.timmy.health.domain.Menu;
import com.timmy.health.domain.Permission;
import com.timmy.health.domain.Role;
import com.timmy.health.domain.RoleAndPermission;
import com.timmy.health.mapper.RoleMapper;
import com.timmy.health.service.RoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@DubboService(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<Role> getRoleByUserId(Integer id) {
        return roleMapper.getRoleByUserId(id);
    }

    @Override
    public IPage<Role> getPages(Integer currenPage, Integer pageSize, Role role) {
        PageHelper.startPage(currenPage, pageSize);
        return roleMapper.getPages(role);
    }

    @Override
    public List<Role> getRoleList(Integer currenPage, Integer pageSize, Role role) {
        PageHelper.startPage(currenPage, pageSize);
        return roleMapper.getRoleList(role);
    }


    @Override
    @Transactional
    public Integer addRole(Role role, Integer[] permissionIds) {
        Integer roleId = roleMapper.save(role);
        List<RoleAndPermission> rolePermissions = new ArrayList<>();
        for (Integer permissionId : permissionIds) {
            RoleAndPermission rolePermission = new RoleAndPermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissions.add(rolePermission);
        }
        if(!rolePermissions.isEmpty()){
            roleMapper.insertRoleAndPermission(rolePermissions);
        }
        return roleId;
    }

    @Override
    @Transactional
    public Integer editRole(Role role, Integer[] permissionIds) {
        roleMapper.editRole(role);
        roleMapper.deletePermissionByRoleId(role.getId());
        List<RoleAndPermission> rolePermissions = new ArrayList<>();
        for (Integer permissionId : permissionIds) {
            RoleAndPermission rolePermission = new RoleAndPermission();
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(permissionId);
            rolePermissions.add(rolePermission);
        }
        roleMapper.insertRoleAndPermission(rolePermissions);
        return role.getId();
    }

    @Override
    public Integer deleteRoleById(Integer id) {
        return roleMapper.deleteById(id);
    }

    @Override
    public List<Permission> getPermissionsByRoleId(Integer roleId) {
        return roleMapper.getPermissionsByRoleId(roleId);
    }

    @Override
    public void deletePermissionByRoleId(Integer id) {
        roleMapper.deletePermissionByRoleId(id);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.selectById(id);
    }
}
