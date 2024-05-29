package com.timmy.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timmy.health.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMapper extends BaseMapper<Member> {

    Integer getMemberSum();

    Integer findMemberCountBeforeDate(String endTime);

    Integer findMemberCountByDate(String date);

    Integer findMemberCountAfterDate(String startTime);

    Integer findMemberTotalCount();

    Integer findMemberCountByDateRange(String beforeDate, String afterDate);

    Integer findMemberIdByUsername(String username);

    Member findById(Integer memberId);
}
