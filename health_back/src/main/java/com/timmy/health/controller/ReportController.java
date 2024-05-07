package com.timmy.health.controller;


import com.timmy.health.constant.MessageConstant;
import com.timmy.health.entity.Result;
import com.timmy.health.service.MemberService;
import com.timmy.health.service.ReportService;
import com.timmy.health.service.SetMealService;
import com.timmy.health.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ResourceLoader resourceLoader;
    @DubboReference(interfaceClass = MemberService.class)
    private MemberService memberService;
    @DubboReference(interfaceClass = SetMealService.class)
    private SetMealService setmealService;

    @DubboReference(interfaceClass = ReportService.class)
    private ReportService reportService;

    @DubboReference(interfaceClass = UserService.class)
    private UserService userService;

    @GetMapping("/getMemberReport")
    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public Result getMemberReport(){
        Map<String,Object> map = new HashMap<>();
        List<String> months = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-12);
        for(int i=0;i<12;i++){
            calendar.add(Calendar.MONTH,1);
            Date date = calendar.getTime();
            months.add(new SimpleDateFormat("yyyy-MM").format(date));
        }
        List<Integer> memberCount = memberService.findMemberCountByMonths(months);
        map.put("memberCount",memberCount);
        return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
    }

    @GetMapping("/getSetmealReport")
    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public Result getSetmealReport(){
        Map<String,Object> data = new HashMap<>();
        try{
            List<Map<String,Object>> setmealCount = setmealService.findSetmealCount();
            data.put("setmealCount",setmealCount);

            List<String> setmealNames = new ArrayList<>();
            for (Map<String, Object> map : setmealCount) {
                String name = (String) map.get("name");
                setmealNames.add(name);
            }
            data.put("setmealNames",setmealNames);
            return new Result(true,MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,data);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }
    }

    @GetMapping("/getBusinessReportData")
    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public Result getBusinessReportData(){
        try{
            Map<String,Object> data = reportService.getBusinessReportData();
            return new Result(true,MessageConstant.GET_BUSINESS_REPORT_SUCCESS,data);
        }catch (Exception e){
            return new Result(false,MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }

    @GetMapping("/getMemberHealthReportData")
    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public Result getMemberHealthReportData(){
        try{
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getName();
            Integer memberId = memberService.findMemberIdByUsername(username);
            if(null == memberId){
                memberId = userService.findUserIdByUsername(username);
            }
            Map<String,Object> data = reportService.getMemberHealthReportData(memberId);
            return new Result(true,MessageConstant.GET_MEMBER_HEALTH_REPORT_SUCCESS,data);
        }catch (Exception e){
            return new Result(false,MessageConstant.GET_MEMBER_HEALTH_REPORT_FAIL);
        }
    }

    //导出运营数据
    @GetMapping("/exportBusinessReport")
    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public Result exportBusinessReport(HttpServletRequest request, HttpServletResponse response){
        try{
            Map<String,Object> result = reportService.getBusinessReportData();
            LocalDate reportDate = (LocalDate) result.get("reportDate");
            // 轉換時間到字符串
            String reportDateStr = reportDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            Integer todayNewMember = (Integer) result.get("todayNewMember");
            Integer totalMember = (Integer) result.get("totalMember");
            Integer thisWeekNewMember = (Integer) result.get("thisWeekNewMember");
            Integer thisMonthNewMember = (Integer) result.get("thisMonthNewMember");
            Integer todayOrderNumber = (Integer) result.get("todayOrderNumber");
            Integer thisWeekOrderNumber = (Integer) result.get("thisWeekOrderNumber");
            Integer thisMonthOrderNumber = (Integer) result.get("thisMonthOrderNumber");
            Integer todayVisitsNumber = (Integer) result.get("todayVisitsNumber");
            Integer thisWeekVisitsNumber = (Integer) result.get("thisWeekVisitsNumber");
            Integer thisMonthVisitsNumber = (Integer) result.get("thisMonthVisitsNumber");
            List<Map> hotSetmeal = (List<Map>) result.get("hotSetmeal");

            String filePath = resourceLoader.getResource("classpath:\\static\\template\\report_template.xlsx").getFile().getAbsolutePath();
            XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(filePath));
            XSSFSheet sheet = excel.getSheetAt(0);

            XSSFRow row = sheet.getRow(2);
            row.getCell(5).setCellValue(reportDateStr);

            row = sheet.getRow(4);
            row.getCell(5).setCellValue(todayNewMember);
            row.getCell(7).setCellValue(totalMember);

            row = sheet.getRow(5);
            row.getCell(5).setCellValue(thisWeekNewMember);
            row.getCell(7).setCellValue(thisMonthNewMember);

            row = sheet.getRow(7);
            row.getCell(5).setCellValue(todayOrderNumber);
            row.getCell(7).setCellValue(todayVisitsNumber);

            row = sheet.getRow(8);
            row.getCell(5).setCellValue(thisWeekOrderNumber);
            row.getCell(7).setCellValue(thisWeekVisitsNumber);

            row = sheet.getRow(9);
            row.getCell(5).setCellValue(thisMonthOrderNumber);
            row.getCell(7).setCellValue(thisMonthVisitsNumber);

            int rowNum = 12;
            for(Map map : hotSetmeal){
                String name = (String) map.get("name");
                Long setmeal_count = (Long) map.get("setmeal_count");
                BigDecimal proportion = (BigDecimal) map.get("proportion");
                row = sheet.getRow(rowNum ++);
                row.getCell(4).setCellValue(name);
                row.getCell(5).setCellValue(setmeal_count);
                row.getCell(6).setCellValue(proportion.doubleValue());
            }

            OutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename=report.xlsx");
            excel.write(out);

            out.flush();
            out.close();
            excel.close();
            return null;
        } catch (Exception e){
            return new Result(false,MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }
}

