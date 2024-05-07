package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_setmeal")
public class Setmeal implements Serializable {

    @NotNull(message = "套餐主鍵不能為空")
    @TableId
    private Integer id;

    @NotBlank
    @TableField(value = "name")
    private String name;
    @NotBlank
    @TableField(value = "code")
    private String code;
    @NotBlank
    @TableField(value = "helpCode")
    private String helpCode;
    @NotBlank
    @TableField(value = "sex")
    private String sex;
    @NotBlank
    @TableField(value = "age")
    private String age;
    @NotBlank
    @TableField(value = "price")
    private Float price;
    @NotBlank
    @TableField(value = "remark")
    private String remark;
    @NotBlank
    @TableField(value = "attention")
    private String attention;
    @NotBlank
    @TableField(value = "img")
    private String img;

    @TableField(select = false,exist = false)
    private List<CheckGroup> checkGroups;
}
