package com.timmy.health;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class HealthBackApplication {
    public static void main(String[] args) {
        //change the system property with the dubbo logger setting
        System.setProperty("dubbo.application.logger","slf4j");
        SpringApplication.run(HealthBackApplication.class, args);
    }
}
