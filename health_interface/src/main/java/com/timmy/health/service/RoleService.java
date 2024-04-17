package com.timmy.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.domain.Permission;
import com.timmy.health.domain.Role;
import com.timmy.health.domain.Setmeal;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Role> getRoleByUserId(Integer id);

    IPage<Role> getPages(Integer currenPage, Integer pageSize, Role role);

    List<Role> getRoleList(Integer currenPage, Integer pageSize, Role role);

    Integer addRole(Role role, Integer[] permissionIds);

    Integer editRole(Role role, Integer[] permissionIds);


    Role getRoleById(Integer id);

    Integer deleteRoleById(Integer id);

    List<Permission> getPermissionsByRoleId(Integer roleId);

    void deletePermissionByRoleId(Integer id);
}
