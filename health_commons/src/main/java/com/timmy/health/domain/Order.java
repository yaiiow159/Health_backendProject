package com.timmy.health.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable{
    public static final String ORDERTYPE_TELEPHONE = "電話預約";
    public static final String ORDERTYPE_WEIXIN = "手機預約";
    public static final String ORDERSTATUS_YES = "已到診";
    public static final String ORDERSTATUS_NO = "未到診";
    private Integer id;
    private Integer memberId;
    private Date orderDate;
    private String orderType;
    private String orderStatus;
    private Integer setmealId;

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Order(Integer memberId, Date orderDate, String orderType, String orderStatus, Integer setmealId) {
        this.memberId = memberId;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.setmealId = setmealId;
    }

    public Order(Integer id, Integer memberId, Date orderDate, String orderType, String orderStatus, Integer setmealId) {
        this.id = id;
        this.memberId = memberId;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.setmealId = setmealId;
    }

}
