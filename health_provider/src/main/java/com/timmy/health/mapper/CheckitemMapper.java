package com.timmy.health.mapper;

import com.timmy.health.domain.CheckItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author examy
* @description 针对表【t_checkitem】的数据库操作Mapper
* @createDate 2022-03-12 04:12:22
* @Entity com.timmy.health.domain.Checkitem
*/
@Repository
public interface CheckitemMapper extends BaseMapper<CheckItem> {

}




