package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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
@Validated
public class CheckGroup implements Serializable {


    @NotNull(message = "檢查項目id不能為null")
    private Integer id;
    private String code;
    private String name;
    private String helpCode;
    private String sex;
    private String remark;
    private String attention;

    @TableField(exist = false)
    private List<CheckItem> checkItems;

}
