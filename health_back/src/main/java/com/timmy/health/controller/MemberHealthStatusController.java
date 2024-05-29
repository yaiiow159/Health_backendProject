package com.timmy.health.controller;

import com.timmy.health.constant.MessageConstant;
import com.timmy.health.domain.MemberHealthStatus;
import com.timmy.health.entity.Result;
import com.timmy.health.service.MemberHealthStatusService;
import com.timmy.health.service.MemberService;
import com.timmy.health.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/memberHealthStatus")
public class MemberHealthStatusController {

    @DubboReference(interfaceClass = MemberHealthStatusService.class)
    private MemberHealthStatusService memberHealthStatusService;

    @DubboReference(interfaceClass = MemberService.class)
    private MemberService memberService;

    @DubboReference(interfaceClass = UserService.class)
    private UserService userService;

    @PostMapping("/editMemeberHealthStatus")
    public Result editMemeberHealthStatus(@RequestBody MemberHealthStatus memberHealthStatus) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Integer memberId = memberService.findMemberIdByUsername(username);
            if(memberId == null) {
                memberId =  userService.findUserIdByUsername(username);
            } else {
                return new Result(false, MessageConstant.INSERT_MEMBER_HEALTH_STATUS_FAIL);
            }
            memberHealthStatus.setMember_id(memberId);
            Integer result = memberHealthStatusService.addMemberHealthStatus(memberHealthStatus);
            if (result == 0) {
                return new Result(false, MessageConstant.INSERT_MEMBER_HEALTH_STATUS_FAIL);
            }
            return new Result(true, MessageConstant.INSERT_MEMBER_HEALTH_STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.INSERT_MEMBER_HEALTH_STATUS_FAIL);
        }
    }
}
