package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timmy.health.domain.MemberHealthStatus;
import com.timmy.health.mapper.MemberHealthStatusMapper;
import com.timmy.health.service.MemberHealthStatusService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@DubboService(interfaceClass = MemberHealthStatusService.class)
public class MemberHealthStatusServiceImpl extends ServiceImpl<MemberHealthStatusMapper, MemberHealthStatus> implements MemberHealthStatusService {

    @Autowired
    private MemberHealthStatusMapper memberHealthStatusMapper;


    @Override
    public Integer addMemberHealthStatus(MemberHealthStatus memberHealthStatus) {
        if(memberHealthStatus.getId() == null) {
            return memberHealthStatusMapper.insert(memberHealthStatus);
        } else {
            return memberHealthStatusMapper.updateById(memberHealthStatus);
        }
    }

}
