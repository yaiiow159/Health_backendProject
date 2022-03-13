package com.timmy.health.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 检查组
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckGroup implements Serializable {
    private Integer id;
    private String code;
    private String name;
    private String helpCode;
    private String sex;
    private String remark;
    private String attention;
    private List<CheckItem> checkItems;//一个检查组合包含多个檢查item
}
