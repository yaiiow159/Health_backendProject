package com.timmy.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timmy.health.domain.Order;
import com.timmy.health.domain.Setmeal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper extends BaseMapper<Order> {
    Integer findOrderCountByDate(String date);
    Integer findOrderCountAfterDate(String date);
    Integer findVisitsCountByDate(String date);
    Integer findVisitsCountAfterDate(String date);
    List<Map> findHotSetmeal();

}
