package com.timmy.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timmy.health.domain.MemberHealthStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberHealthStatusMapper extends BaseMapper<MemberHealthStatus> {
    MemberHealthStatus findByMemberId(Integer memberId);
}
