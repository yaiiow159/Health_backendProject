package com.timmy.health.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 封装查询条件
 */
@Setter
@Getter
public class QueryPageBean implements Serializable{
    private Integer currentPage;
    private Integer pageSize;
    private String queryString;
}