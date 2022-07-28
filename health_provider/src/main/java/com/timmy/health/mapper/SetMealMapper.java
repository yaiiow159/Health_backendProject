package com.timmy.health.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timmy.health.domain.Setmeal;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface SetMealMapper extends BaseMapper<Setmeal> {
    void setSetMealAndCheckGroups(Map<String, Integer> map);

    void addSetMeal(Setmeal setmeal);

    List<Integer> findCheckGroupsById(Integer id);
}
