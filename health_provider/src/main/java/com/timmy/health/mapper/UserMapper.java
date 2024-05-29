package com.timmy.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timmy.health.domain.User;
import com.timmy.health.domain.UserAndRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface UserMapper extends BaseMapper<User> {
    User getUserByName(String username);

    IPage<UserAndRole> getUsersBySpec(Page<UserAndRole> page, User user);

    Integer updateUserProfile(User user);

    Integer register(User user);

    void addUserRole(Map<String, Object> params);

    Integer findUserIdByUsername(String username);

    User findById(Integer memberId);
}
