package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timmy.health.domain.Order;
import com.timmy.health.domain.Setmeal;
import com.timmy.health.mapper.OrderMapper;
import com.timmy.health.service.OrderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@DubboService(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public IPage<Order> getPages(Integer currentPage, Integer pageSize, Order order) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(null != order.getOrderDate(), Order::getOrderDate, order.getOrderDate());
        queryWrapper.like(null != order.getOrderStatus(), Order::getOrderStatus, order.getOrderStatus());
        queryWrapper.like(null != order.getOrderType(), Order::getOrderType, order.getOrderType());

        IPage<Order> orderIPage = this.page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(currentPage, pageSize), queryWrapper);
        return orderIPage;
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderMapper.selectById(id);
    }

    @Override
    public Integer addOrder(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public Integer editOrder(Order order) {
        return orderMapper.updateById(order);
    }

    @Override
    public Integer deleteOrder(Integer id) {
        return orderMapper.deleteById(id);
    }


}
