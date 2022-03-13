package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 检查项
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_checkitem")
public class CheckItem implements Serializable {
    private Integer id;//primary key
    private String code;//code
    private String name;//project name
    private String sex;//sex
    private String age;//
    private Float price;//价格
    private String type;//检查项类型，分为检查和检验两种类型
    private String remark;//项目说明
    private String attention;//注意事项
}
