package com.timmy.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.domain.Menu;

import java.util.List;

public interface MenuService {
    IPage<Menu> getPages(Integer currenPage, Integer pageSize, Menu menu);

    // 取得所有選單
    List<Menu> findAllMenus();

    List<Menu> selectPages(Integer currenPage, Integer pageSize,Menu menu);

    //取得該選單的上層選單
    Menu getParentMenu(Integer parentMenuId);

    Integer add(Menu menu);

    void edit(Menu menu);

    Integer delete(Integer id);

    Menu getMenuById(Integer id);

    List<Menu> getMenuList();
}
