package com.timmy.health.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timmy.health.domain.Setmeal;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public interface SetMealMapper extends BaseMapper<Setmeal> {
    void setSetMealAndCheckGroups(Map map);
    void addSetMeal(Setmeal setmeal);
}
