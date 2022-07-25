package com.timmy.health.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.timmy.health.domain.CheckGroup;

import java.util.List;


public interface CheckgroupService extends IService<CheckGroup> {
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    IPage<CheckGroup> getPages(Integer currentPage, Integer pageSize, CheckGroup checGroup);

    CheckGroup findById(Integer id);

    boolean deleteById(Integer id);

    List<Integer> findCheckItemIds(Integer id);

    boolean deleteGroupItemByGroupId(Integer id);

    void editCheckGroup(CheckGroup checkGroup, Integer[] checkitemIds);

    List<CheckGroup> getAll();
}
