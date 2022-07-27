package com.timmy.health.controller;


import com.timmy.health.constant.MailConstant;
import com.timmy.health.domain.Mail;
import com.timmy.health.entity.Result;
import com.timmy.health.service.MailService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mails")
public class MailController {

    @DubboReference(interfaceClass = MailService.class)
    private MailService mailService;

    @PostMapping("/sendMail/")
    public Result sendEmail(@RequestBody Mail mail) {
        try {
            mailService.sendMail(mail);
            return new Result(true, MailConstant.SEND_MAIL_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MailConstant.SEND_MAIL_FAILDED);
        }
    }
}
