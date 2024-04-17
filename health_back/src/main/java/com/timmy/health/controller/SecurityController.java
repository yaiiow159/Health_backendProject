package com.timmy.health.controller;


import com.timmy.health.constant.UserConstant;
import com.timmy.health.entity.Result;
import com.timmy.health.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/security")
public class SecurityController {

    @DubboReference(interfaceClass = UserService.class)
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @PreAuthorize("permitAll()")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        try {
            if(userService.validateUser(username,password)) {
                return new Result(true, UserConstant.USER_LOGIN_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,UserConstant.USER_LOGIN_FAILURE);
        }
        return new Result(false,UserConstant.USER_LOGIN_FAILURE);
    }

    @ResponseBody
    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public Result register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("birthday" ) @DateTimeFormat(pattern = "yyyy-MM-dd") String birthday,
            @RequestParam("gender") String gender) {
        try {
            if(userService.register(username,password,birthday,gender)){
                return new Result(true, UserConstant.USER_REGISTER_SUCCESS);
            } else {
                return new Result(false,UserConstant.USER_REGISTER_FAILURE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,UserConstant.USER_REGISTER_FAILURE);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @PreAuthorize("permitAll()")
    public Result logout() {
        SecurityContextHolder.clearContext();
        return new Result(true, UserConstant.USER_LOGOUT_SUCCESS);
    }
}
