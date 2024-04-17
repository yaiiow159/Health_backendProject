package com.timmy.health.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.constant.MessageConstant;
import com.timmy.health.domain.Menu;
import com.timmy.health.entity.Result;
import com.timmy.health.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/menus")
public class MenuController {

    @DubboReference(interfaceClass = MenuService.class)
    private MenuService menuService;

    @GetMapping("{currenPage}/{pageSize}")
    public Result findPages(@PathVariable("currenPage") Integer currenPage,
                            @PathVariable("pageSize") Integer pageSize,
                            Menu menu) {
        IPage<Menu> menuPages = menuService.getPages(currenPage, pageSize, menu);
        if (currenPage > menuPages.getPages()) {
            log.warn("當前頁面超過取得頁面數");
            menuPages = menuService.getPages((int) menuPages.getPages(), pageSize, menu);
        }
        return new Result(null != menuPages.getRecords(), menuPages);
    }

    @GetMapping("{id}")
    public Result getMenuById(@PathVariable("id") Integer id) {
        try {
            Menu menu = menuService.getMenuById(id);
            if (menu == null) {
                return new Result(false, MessageConstant.QUERY_MENU_FAIL);
            } else return new Result(true, MessageConstant.QUERY_MENU_SUCCESS, menu);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_MENU_FAIL);
        }
    }

    @GetMapping("/getParentMenu/{id}")
    public Result getParentMenu(@PathVariable("id") Integer id) {
        try {
            Menu parentMenu = menuService.getParentMenu(id);
            if (parentMenu == null) {
                return new Result(false, MessageConstant.QUERY_MENU_FAIL);
            } else return new Result(true, MessageConstant.QUERY_MENU_SUCCESS, parentMenu);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_MENU_FAIL);
        }
    }

    @GetMapping("/getMenuList")
    public Result getMenuList() {
        List<Menu> menuList;
        try {
            menuList = menuService.getMenuList();
            if (menuList == null) {
                return new Result(false, MessageConstant.QUERY_MENU_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_MENU_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_MENU_SUCCESS, menuList);
    }

    @GetMapping()
    public Result findAll() {
        return new Result(true, menuService.findAllMenus());
    }

    @PostMapping
    public Result add(@RequestBody Menu menu) {
        try {
            if (menuService.add(menu) > 0) {
                return new Result(true, MessageConstant.ADD_MENU_SUCCESS);
            } else {
                return new Result(false, MessageConstant.ADD_MENU_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_MENU_FAIL);
        }
    }

    @PutMapping
    public Result edit(@RequestBody Menu menu) {
        try {
            menuService.edit(menu);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_MENU_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_MENU_SUCCESS);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable("id") Integer id) {
        if (menuService.delete(id) > -1) {
            return new Result(true, MessageConstant.DELETE_MENU_SUCCESS);
        } else {
            return new Result(false, MessageConstant.DELETE_MENU_FAIL);
        }
    }
}
