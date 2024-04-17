package com.timmy.health.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.constant.MessageConstant;
import com.timmy.health.domain.Order;
import com.timmy.health.domain.Setmeal;
import com.timmy.health.entity.Result;
import com.timmy.health.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    @DubboReference(interfaceClass = OrderService.class)
    private OrderService orderService;


    @GetMapping("{currentPage}/{pageSize}")
    public Result findPages(@PathVariable("currentPage") Integer currentPage,
                            @PathVariable("pageSize") Integer pageSize,
                            Order order) {

        IPage<Order> orderIPage = orderService.getPages(currentPage, pageSize, order);
        if (currentPage > orderIPage.getPages()) {
            log.warn("當前頁面超過取得頁面數");
            orderIPage = orderService.getPages((int) orderIPage.getPages(), pageSize, order);
        }
        return new Result(null != orderIPage.getRecords(), orderIPage);
    }

    @GetMapping("{id}")
    public Result getOrderById(@PathVariable("id") Integer id) {
        try {
            Order order = orderService.getOrderById(id);
            if (order == null) {
                return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
            } else return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, order);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }

    @PostMapping
    public Result addOrder(@RequestBody Order order) {
        try {
            if(orderService.addOrder(order) == 0){
                return new Result(false, MessageConstant.ORDER_ADD_FAIL);
            } else return new Result(true, MessageConstant.ORDER_ADD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDER_ADD_FAIL);
        }
    }

    @PutMapping
    public Result editOrder(@RequestBody Order order) {
        try {
            if(orderService.editOrder(order) == 0){
                return new Result(false, MessageConstant.ORDER_EDIT_FAIL);
            } else return new Result(true, MessageConstant.ORDER_EDIT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDER_EDIT_FAIL);
        }
    }

    @DeleteMapping("{id}")
    public Result deleteOrder(@PathVariable("id") Integer id) {
        try {
            if(orderService.deleteOrder(id) == 0){
                return new Result(false, MessageConstant.ORDER_DELETE_FAIL);
            } else return new Result(true, MessageConstant.ORDER_DELETE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDER_DELETE_FAIL);
        }
    }

}
