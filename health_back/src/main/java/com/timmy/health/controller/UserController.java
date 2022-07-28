package com.timmy.health.controller;


import com.timmy.health.constant.UserConstant;
import com.timmy.health.domain.User;
import com.timmy.health.entity.Result;
import com.timmy.health.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @DubboReference(interfaceClass = UserService.class)
    private UserService userService;

    @PutMapping("/changePassword")
    @PreAuthorize("hasAuthority('ROLE_EDIT')")
    public Result editPassword(@RequestBody User user){
        try {
            userService.editUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, UserConstant.USER_EDIT_FAILURE);
        }
        return new Result(true,UserConstant.USER_EDIT_SUCCESS);
    }

    @GetMapping("/findUser")
    public Result getUser(String username){
        try {
            User user = userService.findUser(username);
            return new Result(true,user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"沒有這個使用者");
        }
    }
}
