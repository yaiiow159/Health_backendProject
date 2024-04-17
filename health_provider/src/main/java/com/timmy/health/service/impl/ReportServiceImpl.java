package com.timmy.health.service.impl;

import com.timmy.health.mapper.MemberMapper;
import com.timmy.health.mapper.OrderMapper;
import com.timmy.health.mapper.OrderSettingMapper;
import com.timmy.health.service.ReportService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private OrderMapper orderMapper;

    public Map<String, Object> getBusinessReportData() throws Exception{
        LocalDate today = LocalDate.now();
        LocalDate thisWeekMonday = today.with(java.time.DayOfWeek.MONDAY);
        LocalDate firstDay4ThisMonth = today.withDayOfMonth(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayString = today.format(formatter);
        String thisWeekMondayString = thisWeekMonday.format(formatter);
        String firstDay4ThisMonthString = firstDay4ThisMonth.format(formatter);

        Integer todayNewMember = memberMapper.findMemberCountByDate(todayString);//select count(id) from t_member where regTime = today
        Integer totalMember = memberMapper.findMemberTotalCount();
        Integer thisWeekNewMember = memberMapper.findMemberCountAfterDate(thisWeekMondayString);
        Integer thisMonthNewMember = memberMapper.findMemberCountAfterDate(firstDay4ThisMonthString);
        Integer todayOrderNumber = orderMapper.findOrderCountByDate(todayString);
        Integer todayVisitsNumber = orderMapper.findVisitsCountByDate(todayString);
        Integer thisWeekOrderNumber = orderMapper.findOrderCountAfterDate(thisWeekMondayString);
        Integer thisWeekVisitsNumber = orderMapper.findVisitsCountAfterDate(thisWeekMondayString);
        Integer thisMonthOrderNumber = orderMapper.findOrderCountAfterDate(firstDay4ThisMonthString);
        Integer thisMonthVisitsNumber = orderMapper.findVisitsCountAfterDate(firstDay4ThisMonthString);
        List<Map> hotSetmeal = orderMapper.findHotSetmeal();

        Map<String,Object> result = new HashMap<>();
        result.put("reportDate",today);
        result.put("todayNewMember",todayNewMember);
        result.put("totalMember",totalMember);
        result.put("thisWeekNewMember",thisWeekNewMember);
        result.put("thisMonthNewMember",thisMonthNewMember);
        result.put("todayOrderNumber",todayOrderNumber);
        result.put("thisWeekOrderNumber",thisWeekOrderNumber);
        result.put("thisMonthOrderNumber",thisMonthOrderNumber);
        result.put("todayVisitsNumber",todayVisitsNumber);
        result.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        result.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        result.put("hotSetmeal",hotSetmeal);

        return result;
    }
}
