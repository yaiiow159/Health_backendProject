package com.timmy.health.mapper;

import com.timmy.health.domain.CheckGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author TimmyChung
 * @since 2022-04-23
 */
@Repository
public interface CheckgroupMapper extends BaseMapper<CheckGroup> {
    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map<String, Integer> map);

    List<Integer> findCheckItemByGroupId(Integer id);

    boolean deleteCheckItemsByGroupId(Integer id);

    void edit(CheckGroup checkGroup);
}
