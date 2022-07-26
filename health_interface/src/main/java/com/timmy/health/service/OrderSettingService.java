package com.timmy.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timmy.health.domain.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService extends IService<OrderSetting> {
    void add(List<OrderSetting> orderSettingList);

    List<Map<String, Object>> getOrderByDateTime(String date);
}
