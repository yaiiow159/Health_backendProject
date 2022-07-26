package com.timmy.health.controller;


import com.timmy.health.constant.MessageConstant;
import com.timmy.health.domain.OrderSetting;
import com.timmy.health.entity.Result;
import com.timmy.health.service.OrderSettingService;
import com.timmy.health.utils.PoiUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/orderSettings")
public class OrderSettingController {

    @DubboReference(interfaceClass = OrderSettingService.class)
    private OrderSettingService orderSettingService;

    // file upload
    @PostMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile file) {
        try {
            List<String[]> readExcel = PoiUtil.readExcel(file);
            List<OrderSetting> orderSettingList = new ArrayList<>();
            readExcel.forEach(s -> {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat();
                            Date date = sdf.parse(s[0]);
                            OrderSetting orderSetting = new OrderSetting(date, Integer.parseInt(s[1]));
                            orderSettingList.add(orderSetting);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
            );
            orderSettingService.add(orderSettingList);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    @GetMapping(value = "/getOrderDateByMonth/{date}")
    public Result getOrderSettinByDate(@PathVariable String date) {
        try {
            List<Map<String, Object>> dateTime = orderSettingService.getOrderByDateTime(date);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS,dateTime);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }

}
