package com.timmy.health.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.constant.UserConstant;
import com.timmy.health.domain.User;
import com.timmy.health.domain.UserAndRole;
import com.timmy.health.entity.Result;
import com.timmy.health.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @DubboReference(interfaceClass = UserService.class)
    private UserService userService;

    @PutMapping("/changePassword")
    public Result editPassword(@RequestBody User user){
        try {
            userService.editUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, UserConstant.USER_EDIT_FAILURE);
        }
        return new Result(true,UserConstant.USER_EDIT_SUCCESS);
    }

    @PutMapping("/userProfileEdit")
    public Result editUser(@RequestBody User user){
        try {
            userService.editUserProfile(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, UserConstant.USERPROFILE_EDIT_FAILURE);
        }
        return new Result(true,UserConstant.USERPROFILE_EDIT_SUCCESS);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        try {
            userService.removeById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,UserConstant.USER_DELETE_FAILURE);
        }
        return new Result(true,UserConstant.USER_DELETE_SUCCESS);
    }

    @GetMapping("/findUser")
    @PreAuthorize("hasAuthority('USER_QUERY')")
    public Result getUser(String username){
        try {
            User user = userService.findUser(username);
            return new Result(true,user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,UserConstant.USER_FIND_FAILURE);
        }
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(){
        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.getByUserName(username);
            return new Result(true,user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,UserConstant.USER_FIND_FAILURE);
        }
    }

    @GetMapping("{id}")
    public Result getUserById(@PathVariable Integer id){
        User user = userService.getById(id);
        try {
            return new Result(true,user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,UserConstant.USER_FIND_FAILURE);
        }
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public Result findPages(@PathVariable Integer currentPage,
                            @PathVariable Integer pageSize,
                            User user){
        IPage<UserAndRole> userIPage = userService.getPages(currentPage, pageSize, user);
        if (currentPage > userIPage.getPages()) {
            log.info("當前頁面超過取得頁面數");
            userIPage = userService.getPages((int) userIPage.getPages(), pageSize, user);
        }
        return new Result(null != userIPage.getRecords(), userIPage);
    }
}
