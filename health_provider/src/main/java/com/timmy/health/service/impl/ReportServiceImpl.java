package com.timmy.health.service.impl;

import com.timmy.health.domain.Member;
import com.timmy.health.domain.MemberHealthStatus;
import com.timmy.health.domain.User;
import com.timmy.health.mapper.*;
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

    @Autowired
    private MemberHealthStatusMapper memberHealthStatusMapper;

    @Autowired
    private UserMapper userMapper;

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

    @Override
    public Map<String, Object> getMemberHealthReportData(Integer memberId) {
        Map<String,Object> result = new HashMap<>();
        //取得 會員健康數據報告
        LocalDate reportDate = LocalDate.now();
        String reportDateStr = reportDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        MemberHealthStatus memberHealthStatus = memberHealthStatusMapper.findByMemberId(memberId);
        if(null == memberHealthStatus){
            result.put("reportDate",reportDateStr);
            result.put("weight",0);
            result.put("height",0);
            result.put("age",0);
            result.put("BMI",0);
            result.put("BMR",0);
            result.put("calorieIntake",0);
            result.put("bodyFat",0);
            return result;
        }
        result.put("reportDate",reportDateStr);
        result.put("weight",memberHealthStatus.getWeight());
        result.put("height",memberHealthStatus.getHeight());
        result.put("age",memberHealthStatus.getAge());
        // 計算BMI
        double BMI = memberHealthStatus.getWeight() / ((memberHealthStatus.getHeight() / 100) * (memberHealthStatus.getHeight() / 100));
        result.put("BMI",BMI);
        // 計算BMR
        double BMR = 0;
        Member member = memberMapper.selectById(memberId);
        User user = null;
        if(null == member){
            user  = userMapper.selectById(memberId);
        }
        if("1".equals(member.getSex()) || "1".equals(user.getGender())){
            BMR = 66 + (13.7 * memberHealthStatus.getWeight()) + (5 * memberHealthStatus.getHeight()) - (6.8 * memberHealthStatus.getAge());
        } else{
            BMR = 665 + (9.6 * memberHealthStatus.getWeight()) + (1.8 * memberHealthStatus.getHeight()) - (4.7 * memberHealthStatus.getAge());
        }
        result.put("BMR",BMR);
        // 計算卡路里建議攝取量
        double calorieIntake = BMR * 1.2;
        result.put("calorieIntake",calorieIntake);

        // 計算dobyFat
        double bodyFat = (BMR - memberHealthStatus.getCalorieIntake()) / BMR * 100;
        result.put("bodyFat",bodyFat);

        // 計算健康狀態
        String healthStatus = "";
        if(BMI < 18.5){
            healthStatus = MemberHealthStatus.HEALTH_STATUS_VERY_THIN;
        } else if(BMI >= 18.5 && BMI < 24){
            healthStatus = MemberHealthStatus.HEALTH_STATUS_NORMAL;
        } else if(BMI >= 24 && BMI < 27){
            healthStatus = MemberHealthStatus.HEALTH_STATUS_EXCESS;
        } else if(BMI >= 27 && BMI < 30){
            healthStatus = MemberHealthStatus.HEALTH_STATUS_OBESE;
        } else if(BMI >= 30){
            healthStatus = MemberHealthStatus.HEALTH_STATUS_VERY_OBESE;
        }
        result.put("healthStatus",healthStatus);

        return result;
    }
}
