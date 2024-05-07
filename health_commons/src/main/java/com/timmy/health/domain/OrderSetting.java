package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;


/**
 * 
 * @TableName t_ordersetting
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_ordersetting")
public class OrderSetting implements Serializable {
    @TableId(value = "id")
    private Integer id;

    @TableField(value = "orderDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date orderdate;

    @TableField(value = "number")
    private Integer number;

    @TableField(value = "reservations")
    private Integer reservations;

    private static final long serialVersionUID = 1L;

    public OrderSetting(@NotNull Date orderdate, int number){
        this.orderdate = orderdate;
        this.number = number;
    }
}