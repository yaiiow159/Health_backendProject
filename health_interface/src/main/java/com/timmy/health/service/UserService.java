package com.timmy.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timmy.health.domain.User;

public interface UserService extends IService<User> {
    User getByUserName(String username);
}
