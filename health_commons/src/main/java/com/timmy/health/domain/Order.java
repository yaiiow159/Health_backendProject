package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_order")
public class Order implements Serializable {

    public static final String ORDERTYPE_TELEPHONE = "電話預約";
    public static final String ORDERTYPE_WEIXIN = "手機預約";
    public static final String ORDERSTATUS_YES = "已到診";
    public static final String ORDERSTATUS_NO = "未到診";

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Integer id;

    @TableField(value = "member_id")
    private Integer memberId;

    @TableField(value = "orderDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date orderDate;

    @TableField(value = "orderType")
    private String orderType;

    @TableField(value = "orderStatus")
    private String orderStatus;

    @TableField(value = "setmeal_id")
    private Integer setmealId;

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

}
