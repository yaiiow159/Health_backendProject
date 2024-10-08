package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timmy.health.constant.RedisConstant;
import com.timmy.health.domain.Setmeal;
import com.timmy.health.mapper.SetMealMapper;
import com.timmy.health.service.SetMealService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@DubboService(interfaceClass = SetMealService.class)
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, Setmeal> implements SetMealService {

    @Autowired
    private SetMealMapper setMealMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Transactional
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setMealMapper.addSetMeal(setmeal);
        Integer setMealId = setmeal.getId();
        Map<String, Integer> map = setSetMealAndCheckGroupRelate(setMealId, checkgroupIds);
        setMealMapper.setSetMealAndCheckGroups(map);
        redisTemplate.opsForSet().add(RedisConstant.SETMEAL_PIC_DB_RESOURCE, setmeal.getImg());
    }

    @Override
    public IPage<Setmeal> getPages(Integer currenPage, Integer pageSize, @NotNull Setmeal setmeal) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper
                .like(Strings.isNotEmpty(setmeal.getCode()),
                        Setmeal::getCode,
                        setmeal.getCode())
                .like(Strings.isNotEmpty(setmeal.getName()),
                        Setmeal::getName,
                        setmeal.getName())
                .like(Strings.isNotEmpty(setmeal.getHelpCode()),
                        Setmeal::getHelpCode,
                        setmeal.getHelpCode())
                .select(Setmeal::getHelpCode, Setmeal::getAge, Setmeal::getCode, Setmeal::getName, Setmeal::getPrice, Setmeal::getId, Setmeal::getRemark,
                        Setmeal::getAttention, Setmeal::getImg, Setmeal::getSex);

        return setMealMapper.selectPage(new Page<>(currenPage, pageSize), queryWrapper);
    }

    @Override
    public Setmeal getMealById(Integer id) {
        return setMealMapper.selectById(id);
    }

    @Override
    public List<Integer> findCheckGroupById(Integer id) {
        return setMealMapper.findCheckGroupsById(id);
    }

    @Override
    public List<Map<String, Object>> findSetmealCount() {
        return setMealMapper.findSetmealCount();
    }

    @Override
    public List<Setmeal> findAll() {
        return setMealMapper.selectList(null);
    }


    private static @NotNull Map<String, Integer> setSetMealAndCheckGroupRelate(Integer id, Integer[] checkGroupIds) {
        Map<String, Integer> map = new HashMap<>();
        if (checkGroupIds != null) {
            for (Integer checkGroupId : checkGroupIds) {
                map.put("checkGroupId", checkGroupId);
                map.put("setMealId", id);
            }
        }
        return map;
    }

}
