package com.timmy.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timmy.health.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> findAllMenus();

    IPage<Menu> getPages(Page<Menu> page, Menu menu);

    List<Menu> getSelectList(Menu menu);

    Menu findById(Integer id);

    Menu findByParentMenuId(Integer parentMenuId);

    Integer save(Menu menu);

    void edit(Menu menu);
}
