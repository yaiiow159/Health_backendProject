package com.timmy.health.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.constant.MessageConstant;
import com.timmy.health.domain.Permission;
import com.timmy.health.domain.Role;
import com.timmy.health.domain.Setmeal;
import com.timmy.health.entity.Result;
import com.timmy.health.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roles")
public class RoleController {

    @DubboReference(interfaceClass = RoleService.class)
    private RoleService roleService;

    @GetMapping("{currenPage}/{pageSize}")
    public Result findPages(@PathVariable("currenPage") Integer currenPage,
                            @PathVariable("pageSize") Integer pageSize,
                            Role role) {
        IPage<Role> rolePages = roleService.getPages(currenPage, pageSize, role);
        if (currenPage > rolePages.getPages()) {
            log.warn("當前頁面超過取得頁面數");
            rolePages = roleService.getPages((int) rolePages.getPages(), pageSize, role);
        }
        return new Result(null != rolePages.getRecords(), rolePages);
    }

    @GetMapping("{id}")
    public Result getRoleById(@PathVariable("id") Integer id) {
        try {
            Role role = roleService.getRoleById(id);
            if (role == null) {
                return new Result(false, MessageConstant.ROLE_QUERY_FAIL);
            } else return new Result(true, MessageConstant.ROLE_QUERY_SUCCESS, role);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ROLE_QUERY_FAIL);
        }
    }

    @PostMapping("{permissionIds}")
    public Result addRole(@RequestBody Role role, @PathVariable("permissionIds") Integer[] permissionIds) {
        try {
            if(roleService.addRole(role, permissionIds) == 0){
                return new Result(false, MessageConstant.ROLE_ADD_FAIL);
            } else return new Result(true, MessageConstant.ROLE_ADD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ROLE_ADD_FAIL);
        }
    }

    @PutMapping("{permissionIds}")
    public Result editRole(@RequestBody Role role, @PathVariable("permissionIds") Integer[] permissionIds) {
        try {
            if(roleService.editRole(role, permissionIds) == 0){
                return new Result(false, MessageConstant.ROLE_EDIT_FAIL);
            } else return new Result(true, MessageConstant.ROLE_EDIT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ROLE_EDIT_FAIL);
        }
    }

    @DeleteMapping("{id}")
    public Result deleteRoleById(@PathVariable("id") Integer id) {
        try {
           roleService.deletePermissionByRoleId(id);
            if(roleService.deleteRoleById(id) == 0){
                return new Result(false, MessageConstant.ROLE_DELETE_FAIL);
            } else return new Result(true, MessageConstant.ROLE_DELETE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ROLE_DELETE_FAIL);
        }
    }

}
