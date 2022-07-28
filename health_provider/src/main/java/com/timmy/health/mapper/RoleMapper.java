package com.timmy.health.mapper;

import com.timmy.health.domain.Role;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface RoleMapper {

    Set<Role> getRoleByUserId(Integer id);
}
