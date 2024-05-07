package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timmy.health.domain.Role;
import com.timmy.health.domain.User;
import com.timmy.health.domain.UserAndRole;
import com.timmy.health.mapper.PermissionMapper;
import com.timmy.health.mapper.RoleMapper;
import com.timmy.health.mapper.UserMapper;
import com.timmy.health.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


@DubboService(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 使用redis緩存
    @Override
    public User getByUserName(String username) {
        User user = userMapper.getUserByName(username);
        Set<Role> roleList = roleMapper.getRoleByUserId(user.getId());

        roleList.forEach(role -> role.setPermissions(permissionMapper.getPermissionByRoleId(role.getId())));
        user.setRoles(roleList);
        return user;
    }

    @Override
    public void editUser(User user) {
        UpdateWrapper<User> queryWrapper = new UpdateWrapper<>();
        if (Objects.nonNull(user)) {
            queryWrapper.set("password", "username");
            userMapper.update(user, queryWrapper);
        }
    }

    @Override
    public User findUser(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean validateUser(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",password);
        User user = userMapper.selectOne(queryWrapper);
        return Objects.nonNull(user);
    }


    @Override
    public boolean register(String username, String password, String birthday, String gender) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setUsername(username);
        // 加密密碼
        user.setPassword(encoder.encode(password));
        user.setBirthday(date);
        user.setGender(gender);
        Role role = roleMapper.getRoleByName("健康管理師");

        if(userMapper.register(user) > 0) {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", user.getId());
            params.put("roleId", role.getId());
            userMapper.addUserRole(params);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean editUserProfile(User user) {
        if(user.getPassword() != null) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        return userMapper.updateUserProfile(user) > 0;
    }

    @Override
    public IPage<UserAndRole> getPages(Integer currentPage, Integer pageSize, User user) {
        Page<UserAndRole> page = new Page<>(currentPage, pageSize);
        return userMapper.getUsersBySpec(page, user);
    }

    @Override
    public Integer findUserIdByUsername(String username) {
        return userMapper.findUserIdByUsername(username);
    }

}
