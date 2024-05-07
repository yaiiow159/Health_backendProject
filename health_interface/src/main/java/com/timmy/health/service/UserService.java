package com.timmy.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.timmy.health.domain.User;
import com.timmy.health.domain.UserAndRole;

import java.util.Date;
import java.util.List;

public interface UserService extends IService<User> {
    User getByUserName(String username);

    void editUser(User user);

    User findUser(String username);

    boolean validateUser(String username, String password);

    boolean register(String username, String password, String birthday, String gender);

    boolean editUserProfile(User user);

    IPage<UserAndRole> getPages(Integer currentPage, Integer pageSize, User user);

    Integer findUserIdByUsername(String username);
}
