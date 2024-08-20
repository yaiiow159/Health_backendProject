package com.timmy.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timmy.health.domain.Permission;
import com.timmy.health.domain.Role;
import com.timmy.health.domain.RoleAndPermission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface RoleMapper extends BaseMapper<Role> {

    Set<Role> getRoleByUserId(Integer id);

    Role getRoleByName(String name);

    List<Permission> getPermissionsByRoleId(Integer roleId);

    void deletePermissionByRoleId(Integer id);

    void insertRoleAndPermission(List<RoleAndPermission> rolePermissions);

    Integer save(Role role);

    void editRole(Role role);

    List<Role> getRoleList(Role role);

    IPage<Role> getPages(Role role);
}
