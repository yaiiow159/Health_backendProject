package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timmy.health.domain.OrderSetting;
import com.timmy.health.mapper.OrderSettingMapper;
import com.timmy.health.service.OrderSettingService;
import org.apache.dubbo.config.annotation.DubboService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting>
        implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    public void add(@NotNull List<OrderSetting> orderSettingList) {

        if (orderSettingList != null && orderSettingList.size() > 0) {
            orderSettingList.forEach(orderSetting -> {
                        Long countOrderDate = orderSettingMapper.findCountOrderDate(orderSetting.getOrderdate());
                        if (countOrderDate > 0) {
                            // already have settings order update the orderSetting
                            orderSettingMapper.editNumberByOrderDate(orderSetting);
                        } else {
                            // the order is new order so can add
                            orderSettingMapper.insert(orderSetting);
                        }
                    }
            );
        }
    }
}
