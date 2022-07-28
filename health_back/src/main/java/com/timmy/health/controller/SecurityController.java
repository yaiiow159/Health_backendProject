package com.timmy.health.controller;


import com.timmy.health.entity.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @PostMapping("/login")
    public Result login(){
        return null;
    }
}
