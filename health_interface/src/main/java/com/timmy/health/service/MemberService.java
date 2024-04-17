package com.timmy.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.domain.Member;

import java.util.List;

public interface MemberService {
    Integer getMemberSum();

    Integer deleteMemberById(Integer id);

    Integer editMember(Member member);

    Integer addMember(Member member);

    Member getMemberById(Integer id);

    IPage<Member> getPages(Integer currentPage, Integer pageSize, Member member);

    List<Integer> findMemberCountByMonths(List<String> months);

    Integer getMemberSumByDateRange(String beforeDate, String afterDate);
}
