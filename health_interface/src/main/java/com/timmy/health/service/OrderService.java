package com.timmy.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.domain.Order;
import com.timmy.health.domain.Setmeal;

public interface OrderService {
    IPage<Order> getPages(Integer currentPage, Integer pageSize, Order order);

    Order getOrderById(Integer id);

    Integer addOrder(Order order);

    Integer editOrder(Order order);

    Integer deleteOrder(Integer id);
}
