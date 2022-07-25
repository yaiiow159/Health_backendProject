package com.timmy.health.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果封装对象
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResult implements Serializable{
    private Long total;
    private List<Object> rows;
}
