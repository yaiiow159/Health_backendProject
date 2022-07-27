package com.timmy.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.timmy.health.domain.CheckItem;

import java.util.List;


public interface CheckItemService extends IService<CheckItem> {
    boolean save(CheckItem checkItem);

    IPage<CheckItem> findPage(int currenPage, int pageSize);

    IPage<CheckItem> findPage(int currenPage, int pageSize, CheckItem checkItem);

    List<CheckItem> getAllCheckItems();

    int edit(CheckItem checkItem);
}

