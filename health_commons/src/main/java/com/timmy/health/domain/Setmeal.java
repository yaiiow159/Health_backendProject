package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Data
@Validated
public class Setmeal implements Serializable {

    @NotNull(message = "套餐主鍵不能為空")
    @TableId
    private Integer id;

    @NotBlank
    private String name;
    @NotBlank
    private String code;
    @NotBlank
    private String helpCode;
    @NotBlank
    private String sex;
    @NotBlank
    private String age;
    @NotBlank
    private Float price;
    @NotBlank
    private String remark;
    @NotBlank
    private String attention;
    @NotBlank
    private String img;

    @TableField(exist = false)
    private List<CheckGroup> checkGroups;//many to many
}
