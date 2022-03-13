package com.timmy.health.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class ResolveConfig {

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(1024 * 1024 * 1024);
        return resolver;
    }
}
