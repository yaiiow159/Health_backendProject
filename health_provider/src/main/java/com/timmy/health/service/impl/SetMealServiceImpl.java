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
import java.util.Map;

@DubboService(interfaceClass = SetMealService.class)
@Transactional
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper,Setmeal> implements SetMealService {

    @Autowired
    private SetMealMapper setMealMapper;
    //use to set the img name to make sure the img is send to database or not
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setMealMapper.addSetMeal(setmeal);
        Integer setMealId = setmeal.getId();
        Map<String, Integer> map = setSetMealAndCheckGroupRelate(setMealId, checkgroupIds);
        setMealMapper.setSetMealAndCheckGroups(map);
        //make sure if the img have not add to the database
        redisTemplate.opsForSet().add(RedisConstant.SETMEAL_PIC_DB_RESOURCE, setmeal.getImg());
    }

    @Override
    public IPage<Setmeal> getPages(Integer currenPage, Integer pageSize, @NotNull Setmeal setmeal) {

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();

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
                .select(Setmeal::getHelpCode,Setmeal::getAge,Setmeal::getCode,Setmeal::getName,Setmeal::getPrice,Setmeal::getId,Setmeal::getRemark,
                        Setmeal::getAttention,Setmeal::getImg,Setmeal::getSex);

        return setMealMapper.selectPage(new Page<>(currenPage,pageSize),queryWrapper);
    }


    private static @NotNull Map<String, Integer> setSetMealAndCheckGroupRelate(Integer id, Integer[] checkGroupIds) {
        Map<String, Integer> map = new HashMap<>();
        if (checkGroupIds != null && checkGroupIds.length != 0) {
            for (Integer checkGroupId : checkGroupIds) {
                map.put("checkGroupId", checkGroupId);
                map.put("setMealId", id);
            }
        }
        return map;
    }
}
