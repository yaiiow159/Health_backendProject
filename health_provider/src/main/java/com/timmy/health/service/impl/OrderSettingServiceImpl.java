package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timmy.health.domain.OrderSetting;
import com.timmy.health.mapper.OrderSettingMapper;
import com.timmy.health.service.OrderSettingService;
import org.apache.dubbo.config.annotation.DubboService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@DubboService(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting>
        implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    public void add(@NotNull List<OrderSetting> orderSettingList) {
        if (orderSettingList.size() > 0) {
            orderSettingList.forEach(orderSetting -> {
                        Long countOrderDate = orderSettingMapper.findCountOrderDate(orderSetting.getOrderdate());
                        if (countOrderDate > 0) {
                            // already have settings order update the orderSetting
                            orderSettingMapper.editNumberByOrderDate(orderSetting);
                        } else {
                            // the order is new order so can add
                            orderSettingMapper.add(orderSetting);
                        }
                    }
            );
        }

    }

    @Override
    public List<Map<String, Object>> getOrderByDateTime(String date) {
        Map<String, String> dateMap = new HashMap<>();

        String firstDay = date + "-01";
        String lastDay = date + "-31";

        dateMap.put("firstday", firstDay);
        dateMap.put("lastday", lastDay);

        List<OrderSetting> orderList = orderSettingMapper.getOrderDateByCurrentMonth(dateMap);
        List<Map<String, Object>> newOrderList = new ArrayList<>();

        if (null != orderList && orderList.size() > 0) {
            orderList.forEach(orderSetting -> {
                HashMap<String, Object> map = new HashMap<>();
                map.put("date", orderSetting.getOrderdate().getDate());
                map.put("number", orderSetting.getNumber());
                map.put("reservations", orderSetting.getReservations());
                newOrderList.add(map);
            });
        }
        return newOrderList;
    }

    @Override
    public void editNumberByDate(@NotNull OrderSetting orderSetting) {
        Date orderDate = orderSetting.getOrderdate();
        long count = orderSettingMapper.findCountOrderDate(orderDate);
        if (count > 0) {
            orderSettingMapper.editNumberByOrderDate(orderSetting);
        } else {
            orderSettingMapper.add(orderSetting);
        }
    }
}
