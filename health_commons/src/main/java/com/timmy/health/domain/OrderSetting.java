package com.timmy.health.domain;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleDropDbLinkStatement;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * 
 * @TableName t_ordersetting
 */
@TableName(value ="t_ordersetting")
@Data
@NoArgsConstructor
@Validated
public class OrderSetting implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull
    private Integer id;

    /**
     * 約預日期
     */
    @TableField(value = "orderDate")
    private Date orderdate;

    /**
     * 可預約人數
     */
    @TableField(value = "number")
    private Integer number;

    /**
     * 已預約人數
     */
    @TableField(value = "reservations")
    private Integer reservations;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public OrderSetting(Date orderdate, int number){
        this.orderdate = orderdate;
        this.number = number;
    }
}