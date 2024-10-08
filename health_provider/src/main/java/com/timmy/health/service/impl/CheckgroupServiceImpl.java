package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timmy.health.domain.CheckGroup;
import com.timmy.health.mapper.CheckgroupMapper;
import com.timmy.health.service.CheckgroupService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TimmgChung
 * @description 针对表【t_checkgroup】的数据库操作Service实现
 * @createDate 2022-04-17 02:59:38
 */
@DubboService(interfaceClass = CheckgroupService.class)
public class CheckgroupServiceImpl extends ServiceImpl<CheckgroupMapper, CheckGroup> implements CheckgroupService {

    @Autowired
    private CheckgroupMapper checkgroupMapper;

    private void checkedMapInput(CheckGroup t, Integer[] checkitemIds) {
        Map<String, Integer> map = new HashMap<>();
        if (null != checkitemIds) {
            for (Integer checkitemId : checkitemIds) {
                map.put("checkgroupId", t.getId());
                map.put("checkitemId", checkitemId);
                checkgroupMapper.setCheckGroupAndCheckItem(map);
            }
        }
    }

    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkgroupMapper.add(checkGroup);
        checkedMapInput(checkGroup, checkitemIds);
    }

    @Override
    public IPage<CheckGroup> getPages(Integer currentPage, Integer pageSize, @NotNull CheckGroup checkGroup) {
        LambdaQueryWrapper<CheckGroup> queryWrapper = new LambdaQueryWrapper<>();
        //t_checkgroup(code,name,sex,helpCode,remark,attention)
        queryWrapper
                .like(Strings.isNotEmpty(checkGroup.getName()),
                        CheckGroup::getName,
                        checkGroup.getName())
                .like(Strings.isNotEmpty(checkGroup.getCode()),
                        CheckGroup::getCode,
                        checkGroup.getCode())
                .like(Strings.isNotEmpty(checkGroup.getHelpCode()),
                        CheckGroup::getHelpCode,
                        checkGroup.getHelpCode())
                .select(CheckGroup::getId,
                        CheckGroup::getCode,
                        CheckGroup::getName,
                        CheckGroup::getHelpCode,
                        CheckGroup::getRemark,
                        CheckGroup::getAttention);

        return checkgroupMapper.selectPage(new Page<>(currentPage, pageSize), queryWrapper);
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkgroupMapper.selectById(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        return checkgroupMapper.deleteById(id) > -1;
    }

    @Override
    public List<Integer> findCheckItemIds(Integer id) {
        return checkgroupMapper.findCheckItemByGroupId(id);
    }

    @Override
    public void deleteGroupItemByGroupId(Integer id) {
        checkgroupMapper.deleteCheckItemsByGroupId(id);
    }

    @Override
    public void editCheckGroup(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkgroupMapper.edit(checkGroup);
        checkgroupMapper.deleteCheckItemsByGroupId(checkGroup.getId());
        checkedMapInput(checkGroup, checkitemIds);
    }

    @Override
    public List<CheckGroup> getAll() {
        return checkgroupMapper.selectList(null);
    }

}




