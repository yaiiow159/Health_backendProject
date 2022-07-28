package com.timmy.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timmy.health.domain.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends BaseMapper<User> {
    User getUserByName(String username);
}
