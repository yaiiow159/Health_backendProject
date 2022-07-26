package com.timmy.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timmy.health.domain.OrderSetting;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderSettingMapper extends BaseMapper<OrderSetting> {
    void editNumberByOrderDate(OrderSetting orderSetting);

    Long findCountOrderDate(Date orderDate);

    List<OrderSetting> getOrderDateByCurrentMonth(Map<String, String> dateMap);

    void add(OrderSetting orderSetting);

}
