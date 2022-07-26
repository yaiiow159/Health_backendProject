package com.timmy.health;

import com.timmy.health.utils.MybatisGenerator;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDubbo
@EnableTransactionManagement
@EnableScheduling
@MapperScan({"com.timmy.health.mapper"})
public class HealthProviderApplication {

    public static void main(String[] args) {
        //set the dubbo.system property logger setting to log4j2
        System.setProperty("dubbo.application.logger","slf4j");
        SpringApplication.run(HealthProviderApplication.class, args);
    }

}
