package com.timmy.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.timmy.health.domain.Setmeal;

public interface SetMealService extends IService<Setmeal> {

    void add(Setmeal setmeal, Integer[] checkgroupIds);

    IPage<Setmeal> getPages(Integer currenPage, Integer pageSize, Setmeal setmeal);
}

