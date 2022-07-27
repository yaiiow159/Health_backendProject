package com.timmy.health.service.impl;

import com.timmy.health.domain.Mail;
import com.timmy.health.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Slf4j
@DubboService(interfaceClass = MailService.class)
public class MailServiceImpl implements MailService {

    private static final String GETTER = "examyou076@gmail.com";

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(@NotNull Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getAddress());
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());
        message.setTo(GETTER);

        try {
            javaMailSender.send(message);
            log.info("郵件發送出去");
        } catch (MailException e) {
            e.printStackTrace();
            log.error("傳送郵件發生異常");
        }
    }
}
