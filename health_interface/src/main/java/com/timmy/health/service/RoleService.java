package com.timmy.health.service;

import com.timmy.health.domain.Role;
import java.util.Set;

public interface RoleService {
    Set<Role> getRoleByUserId(Integer id);
}
