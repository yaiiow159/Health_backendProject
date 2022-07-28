package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timmy.health.domain.Role;
import com.timmy.health.domain.User;
import com.timmy.health.mapper.PermissionMapper;
import com.timmy.health.mapper.RoleMapper;
import com.timmy.health.mapper.UserMapper;
import com.timmy.health.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;


@DubboService(interfaceClass = UserService.class)
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User getByUserName(String username) {
        User user = userMapper.getUserByName(username);
        Set<Role> roleList = roleMapper.getRoleByUserId(user.getId());

        roleList.forEach(role -> role.setPermissions(permissionMapper.getPermissionByRoleId(role.getId())));
        user.setRoles(roleList);
        return user;
    }



}
