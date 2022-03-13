package com.timmy.health;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDubbo
@EnableTransactionManagement
@MapperScan("com.timmy.health.mapper")
public class HealthProviderApplication {

    public static void main(String[] args) {
        System.setProperty("dubbo.application.logger","log4j2");
        SpringApplication.run(HealthProviderApplication.class, args);
    }

}
