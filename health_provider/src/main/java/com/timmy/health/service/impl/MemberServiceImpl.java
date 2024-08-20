package com.timmy.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timmy.health.domain.Member;
import com.timmy.health.mapper.MemberMapper;
import com.timmy.health.service.MemberService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DubboService(interfaceClass = MemberService.class)
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Integer getMemberSum() {
        return memberMapper.getMemberSum();
    }

    @Override
    public Integer deleteMemberById(Integer id) {
        return memberMapper.deleteById(id);
    }

    @Override
    public Integer editMember(Member member) {
        if(member.getPassword() != null) {
            try {
                String hashPassword = hashPassword(member.getPassword());
                member.setPassword(hashPassword);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return memberMapper.updateById(member);
    }

    @Override
    public Integer addMember(Member member) {
        if(member.getPassword() != null) {
            try {
                String hashPassword = hashPassword(member.getPassword());
                member.setPassword(hashPassword);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        LocalDateTime nowTime = LocalDateTime.now();
        Date regTime = Date.from(nowTime.atZone(ZoneId.systemDefault()).toInstant());
        member.setRegTime(regTime);
        member.setRemark("register");
        return memberMapper.insert(member);
    }

    @Override
    public Member getMemberById(Integer id) {
        return memberMapper.selectById(id);
    }

    @Override
    public IPage<Member> getPages(Integer currentPage, Integer pageSize, Member member) {
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(member.getName() != null, Member::getName, member.getName());
        queryWrapper.like(member.getPhoneNumber() != null, Member::getPhoneNumber, member.getPhoneNumber());
        queryWrapper.like(member.getSex() != null, Member::getSex, member.getSex());
        queryWrapper.like(member.getEmail() != null, Member::getEmail, member.getEmail());
        return memberMapper.selectPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(currentPage, pageSize), queryWrapper);
    }

    @Override
    public List<Integer> findMemberCountByMonths(List<String> months) {
        List<Integer> memberCounts = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        for (String month : months) {
            try {
                YearMonth yearMonth = YearMonth.parse(month, formatter);
                LocalDate endOfMonth = yearMonth.atEndOfMonth();
                String date = endOfMonth.toString();

                Integer count = memberMapper.findMemberCountBeforeDate(date);
                memberCounts.add(count);
            } catch (Exception e) {
                log.error("error", e);
                memberCounts.add(0);
            }
        }
        return memberCounts;
    }
    @Override
    public Integer getMemberSumByDateRange(String beforeDate, String afterDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date beforeTime = null;
        Date afterTime = null;
        try {
            beforeTime = simpleDateFormat.parse(beforeDate);
            afterTime = simpleDateFormat.parse(afterDate);
            if(beforeTime != null && afterTime == null) {
                return memberMapper.findMemberCountBeforeDate(simpleDateFormat.format(beforeTime));
            } else if (beforeTime == null && afterTime != null) {
                return memberMapper.findMemberCountAfterDate(simpleDateFormat.format(afterTime));
            } else if (beforeTime != null && afterTime != null) {
                return memberMapper.findMemberCountByDateRange(simpleDateFormat.format(beforeTime), simpleDateFormat.format(afterTime));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer findMemberIdByUsername(String username) {
        return memberMapper.findMemberIdByUsername(username);
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.selectList(null);
    }

    /**
     * 使用sha256加密密碼
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(password.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }


}
