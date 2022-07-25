package com.timmy.health.domain;

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
@Validated
public class CheckItem implements Serializable {

    @NotNull(message = "檢查項目id不能為空")
    private Integer id;//primary key
    private String code;//code
    private String name;//project name
    private String sex;//sex
    private String age;//
    private Float price;// price
    private String type;//
    private String remark;
    private String attention;
}
