package com.timmy.health;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class HealthBackApplication {

    public static void main(String[] args) {
        System.setProperty("dubbo.application.logger","log4j2");
        SpringApplication.run(HealthBackApplication.class, args);
    }

}
