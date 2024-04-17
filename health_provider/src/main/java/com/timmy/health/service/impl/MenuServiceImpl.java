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
@Transactional
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
        Menu parentMenu = menuMapper.findByParentMenuId(menu.getParentMenuId());
        return parentMenu;
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
        return menuMapper.selectById(id);
    }

    @Override
    public List<Menu> getMenuList() {
        List<Menu> menuList = menuMapper.findAllMenus();
//        Map<Integer, Menu> menuMap = new HashMap<>();
//        menuList.forEach(menu -> {
//            menuMap.put(menu.getId(), menu);
//        });
//        List<Menu> menuTree = new ArrayList<>();
//        menuList.forEach(menu -> {
//            if (menu.getParentMenuId() == 0) {
//                menuTree.add(menu);
//            } else {
//                Menu parentMenu = menuMap.get(menu.getParentMenuId());
//                parentMenu.getChildren().add(menu);
//            }
//        });
//        // 按照Path順序 排序菜單
//        menuTree.forEach(this::sortMenuChildren);
        return menuList;
    }

    private void sortMenuChildren(Menu menu) {
        if (menu.getChildren().isEmpty()) {
            return;
        }
        menu.getChildren().sort(Comparator.comparing(Menu::getPath));
        menu.getChildren().forEach(this::sortMenuChildren);
    }

}
