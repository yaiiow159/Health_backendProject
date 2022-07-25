package com.timmy.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timmy.health.domain.OrderSetting;

import java.util.List;

public interface OrderSettingService extends IService<OrderSetting> {

    void add(List<OrderSetting> orderSettingList);
}
