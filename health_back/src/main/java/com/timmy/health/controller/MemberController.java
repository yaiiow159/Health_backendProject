package com.timmy.health.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.constant.MessageConstant;
import com.timmy.health.domain.Member;
import com.timmy.health.domain.User;
import com.timmy.health.domain.UserAndRole;
import com.timmy.health.entity.Result;
import com.timmy.health.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/members")
public class MemberController {

    @DubboReference(interfaceClass = MemberService.class)
    private MemberService memberService;

    @GetMapping("/sum")
    public Integer getMemberSum(
            @RequestParam(value = "beforeDate", required = false) String beforeDate,
            @RequestParam(value = "afterDate", required = false) String afterDate) {
        if (beforeDate != null || afterDate != null) {
            return memberService.getMemberSumByDateRange(beforeDate, afterDate);
        } else {
            return memberService.getMemberSum();
        }
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public Result findPages(@PathVariable Integer currentPage,
                            @PathVariable Integer pageSize,
                            Member member){
        IPage<Member> userIPage = memberService.getPages(currentPage, pageSize, member);
        if (currentPage > userIPage.getPages()) {
            log.info("當前頁面超過取得頁面數");
            userIPage = memberService.getPages((int) userIPage.getPages(), pageSize, member);
        }
        return new Result(null != userIPage.getRecords(), userIPage);
    }

    @GetMapping("{id}")
    public Result getMemberById(@PathVariable("id") Integer id) {
        try {
            Member member = memberService.getMemberById(id);
            if (member == null) {
                return new Result(false, MessageConstant.MEMBER_QUERY_FAIL);
            } else return new Result(true, MessageConstant.MEMBER_QUERY_SUCCESS, member);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.MEMBER_QUERY_FAIL);
        }
    }

    @PostMapping
    public Result addMember(@RequestBody Member member) {
        try {
            if(memberService.addMember(member) == 0){
                return new Result(false, MessageConstant.MEMBER_ADD_FAIL);
            } else return new Result(true, MessageConstant.MEMBER_ADD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.MEMBER_ADD_FAIL);
        }
    }

    @PutMapping
    public Result editMember(@RequestBody Member member) {
        try {
            if(memberService.editMember(member) == 0){
                return new Result(false, MessageConstant.MEMBER_EDIT_FAIL);
            } else return new Result(true, MessageConstant.MEMBER_EDIT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.MEMBER_EDIT_FAIL);
        }
    }

    @DeleteMapping("{id}")
    public Result deleteMemberById(@PathVariable("id") Integer id) {
        try {
            if(memberService.deleteMemberById(id) == 0){
                return new Result(false, MessageConstant.MEMBER_DELETE_FAIL);
            } else return new Result(true, MessageConstant.MEMBER_DELETE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.MEMBER_DELETE_FAIL);
        }
    }

}
