package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 检查组
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_checkgroup")
public class CheckGroup implements Serializable {
    @NotNull(message = "檢查項目id不能為null")
    @TableId(value = "id")
    private Integer id;

    @TableField(value = "code")
    private String code;

    @TableField(value = "name")
    private String name;

    @TableField(value = "helpCode")
    private String helpCode;

    @TableField(value = "sex")
    private String sex;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "attention")
    private String attention;

    @TableField(select = false,exist = false)
    private List<CheckItem> checkItems;

}
