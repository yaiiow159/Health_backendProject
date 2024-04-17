package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timmy.health.domain.OrderSetting;
import com.timmy.health.mapper.OrderSettingMapper;
import com.timmy.health.service.OrderSettingService;
import org.apache.dubbo.config.annotation.DubboService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

@DubboService(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting> implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    public void add(@NotNull List<OrderSetting> orderSettingList) {
        if (!orderSettingList.isEmpty()) {
            orderSettingList.forEach(orderSetting -> {
                        Long countOrderDate = orderSettingMapper.findCountOrderDate(orderSetting.getOrderdate());
                        if (countOrderDate > 0) {
                            orderSettingMapper.editNumberByOrderDate(orderSetting);
                        } else {
                            orderSettingMapper.add(orderSetting);
                        }
                    }
            );
        }
    }

    @Override
    public List<Map<String, Object>> getOrderByDateTime(@NotNull String date) {
        Map<String, String> dateMap = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M");

        YearMonth yearMonth = YearMonth.parse(date, formatter);
        LocalDate firstDay = yearMonth.atDay(1);
        LocalDate lastDay = yearMonth.atEndOfMonth();

        dateMap.put("firstday", firstDay.toString());
        dateMap.put("lastday", lastDay.toString());

        List<OrderSetting> orderList = orderSettingMapper.getOrderDateByCurrentMonth(dateMap);
        List<Map<String, Object>> newOrderList = new ArrayList<>();

        if (null != orderList && !orderList.isEmpty()) {
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
