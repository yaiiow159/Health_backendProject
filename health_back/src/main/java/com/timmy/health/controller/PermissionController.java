package com.timmy.health.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.constant.MessageConstant;
import com.timmy.health.domain.Permission;
import com.timmy.health.entity.Result;
import com.timmy.health.service.PerMissionService;
import com.timmy.health.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @DubboReference(interfaceClass = PerMissionService.class)
    private PerMissionService perMissionService;

    @DubboReference(interfaceClass = RoleService.class)
    private RoleService roleService;

    @GetMapping("{currenPage}/{pageSize}")
    public Result findPages(@PathVariable("currenPage") Integer currenPage,
                            @PathVariable("pageSize") Integer pageSize,
                            Permission permission) {
        IPage<Permission> perMissionPages = perMissionService.getPages(currenPage, pageSize, permission);
        if (currenPage > perMissionPages.getPages()) {
            log.warn("當前頁面超過取得頁面數");
            perMissionPages = perMissionService.getPages((int) perMissionPages.getPages(), pageSize, permission);
        }
        return new Result(null != perMissionPages.getRecords(), perMissionPages);
    }

    @GetMapping("{id}")
    public Result getPermissionById(@PathVariable("id") Integer id) {
        try {
            Permission permission = perMissionService.getPermissionById(id);
            if (permission == null) {
                return new Result(false, MessageConstant.PERMISSION_QUERY_FAIL);
            } else return new Result(true, MessageConstant.PERMISSION_QUERY_SUCCESS, permission);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PERMISSION_QUERY_FAIL);
        }
    }

    @GetMapping("/findAll")
    public Result getAllPermission() {
        try {
            List<Permission> permissions = perMissionService.getAllPermission();
            return new Result(true, MessageConstant.PERMISSION_QUERY_SUCCESS, permissions);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PERMISSION_QUERY_FAIL);
        }
    }

    @GetMapping("/getPermissionsByRoleId/{roleId}")
    public List<Permission> getPermissionsByRoleId(@PathVariable("roleId") Integer roleId) {
        return roleService.getPermissionsByRoleId(roleId);
    }

    @PostMapping
    public Result addPermission(@RequestBody Permission permission) {
        try {
            if(perMissionService.addPermission(permission) == 0){
                return new Result(false, MessageConstant.PERMISSION_ADD_FAIL);
            } else return new Result(true, MessageConstant.PERMISSION_ADD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PERMISSION_ADD_FAIL);
        }
    }

    @PutMapping
    public Result editPermission(@RequestBody Permission permission) {
        try {
            if(perMissionService.editPermission(permission) == 0){
                return new Result(false, MessageConstant.PERMISSION_EDIT_FAIL);
            } else return new Result(true, MessageConstant.PERMISSION_EDIT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PERMISSION_EDIT_FAIL);
        }
    }

    @DeleteMapping("{id}")
    public Result deletePermission(@PathVariable("id") Integer id) {
        try {
            if(perMissionService.deletePermission(id) == 0){
                return new Result(false, MessageConstant.PERMISSION_DELETE_FAIL);
            } else return new Result(true, MessageConstant.PERMISSION_DELETE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PERMISSION_DELETE_FAIL);
        }
    }
}
