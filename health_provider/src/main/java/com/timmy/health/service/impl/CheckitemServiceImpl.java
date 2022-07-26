package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timmy.health.domain.CheckItem;
import com.timmy.health.service.CheckItemService;
import com.timmy.health.mapper.CheckitemMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
* @author examy
* @description 针对表【t_checkitem】的数据库操作Service实现
* @createDate 2022-03-12 04:12:22
*/
@DubboService(interfaceClass = CheckItemService.class)
@Transactional
public class CheckitemServiceImpl extends ServiceImpl<CheckitemMapper, CheckItem> implements CheckItemService{

    @Autowired
    private CheckitemMapper checkitemMapper;

    @Override
    public boolean save(CheckItem checkItem) {
         return checkitemMapper.insert(checkItem) > 0;
    }

    @Override
    public IPage<CheckItem> findPage(int currenPage, int pageSize) {
        return checkitemMapper.selectPage(new Page<>(currenPage, pageSize),null);
    }

    @Override
    public IPage<CheckItem> findPage(int currenPage, int pageSize, @NotNull CheckItem checkItem) {
        LambdaQueryWrapper<CheckItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(checkItem.getName()),CheckItem::getName,checkItem.getName());
        queryWrapper.like(Strings.isNotEmpty(checkItem.getCode()),CheckItem::getCode,checkItem.getCode());

        return checkitemMapper.selectPage(new Page<>(currenPage, pageSize),queryWrapper);
    }

    @Override
    public List<CheckItem> getAllCheckItems() {
        return checkitemMapper.selectList(null);
    }

    @Override
    public int edit(@NotNull CheckItem checkItem) {
        UpdateWrapper<CheckItem> updateWrapper = new UpdateWrapper<>();
        update().eq("id",checkItem.getId());
        return checkitemMapper.update(checkItem,updateWrapper);
    }

}




