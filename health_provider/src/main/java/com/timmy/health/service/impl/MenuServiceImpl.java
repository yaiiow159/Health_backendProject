package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.timmy.health.domain.Menu;
import com.timmy.health.mapper.MenuMapper;
import com.timmy.health.service.MenuService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@DubboService(interfaceClass = MenuService.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public IPage<Menu> getPages(Integer currenPage, Integer pageSize, Menu menu) {
        Page<Menu> page = new Page<>(currenPage, pageSize);
        return menuMapper.getPages(page, menu);
    }

    @Override
    public List<Menu> findAllMenus() {
        return menuMapper.findAllMenus();
    }

    @Override
    public List<Menu> selectPages(Integer currenPage, Integer pageSize, Menu menu) {
        PageHelper.startPage(currenPage, pageSize);
        return menuMapper.getSelectList(menu);
    }

    //取得該選單的上層選單
    @Override
    public Menu getParentMenu(Integer id) {
        // 先查詢初當前選單
        Menu menu = menuMapper.findById(id);
        // 獲取上層選單
        return menuMapper.findByParentMenuId(menu.getParentMenuId());
    }

    @Override
    public Integer add(Menu menu) {
        return menuMapper.save(menu);
    }

    @Override
    public void edit(Menu menu) {
         menuMapper.edit(menu);
    }

    @Override
    public Integer delete(Integer id) {
        return menuMapper.deleteById(id);
    }

    @Override
    public Menu getMenuById(Integer id) {
        return menuMapper.findById(id);
    }

    @Override
    public List<Menu> getMenuList() {
        List<Menu> allMenus = menuMapper.findAllMenus();
        return buildMenuTree(allMenus);
    }

    private List<Menu> buildMenuTree(List<Menu> allMenus) {
        List<Menu> rootMenus = new ArrayList<>();
        Map<Integer, Menu> menuMap = new HashMap<>();

        // 將所有菜單存入 Map 方便查找
        for (Menu menu : allMenus) {
            menuMap.put(menu.getId(), menu);
        }

        // 組建樹狀結構
        for (Menu menu : allMenus) {
            if (menu.getParentMenuId() == null) {
                rootMenus.add(menu);
                // 針對 path 進行排序
                rootMenus.sort(Comparator.comparing(Menu::getPath));
            } else {
                Menu parent = menuMap.get(menu.getParentMenuId());
                if (parent != null) {
                    parent.getChildren().add(menu);
                    // 針對 path 進行排序
                    parent.getChildren().sort(Comparator.comparing(Menu::getPath));
                }
            }
        }
        return rootMenus;
    }


}
