package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * checkItems
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_checkitem")
public class CheckItem implements Serializable {

    @NotNull(message = "檢查項目id不能為空")
    private Integer id;

    @TableField(value = "code")
    private String code;

    @TableField(value = "name")
    @NotNull(message = "檢查項目名稱不能為空")
    private String name;

    @TableField(value = "sex")
    private String sex;//sex

    @TableField(value = "age")
    private String age;//

    @TableField(value = "price")
    private Float price;// price

    @TableField(value = "type")
    private String type;//

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "attention")
    private String attention;
}
