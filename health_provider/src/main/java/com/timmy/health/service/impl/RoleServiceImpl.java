package com.timmy.health.service.impl;

import com.timmy.health.domain.Role;
import com.timmy.health.mapper.RoleMapper;
import com.timmy.health.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<Role> getRoleByUserId(Integer id) {
        return roleMapper.getRoleByUserId(id);
    }
}
