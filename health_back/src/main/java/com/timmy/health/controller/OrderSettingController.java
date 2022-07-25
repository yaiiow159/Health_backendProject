package com.timmy.health.controller;


import com.timmy.health.constant.MessageConstant;
import com.timmy.health.domain.OrderSetting;
import com.timmy.health.entity.Result;
import com.timmy.health.service.OrderSettingService;
import com.timmy.health.utils.PoiUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                        OrderSetting orderSetting = new OrderSetting(new Date(s[0]),Integer.valueOf(s[1]));
                        orderSettingList.add(orderSetting);
                    }
            );
            orderSettingService.add(orderSettingList);
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

}
