package com.timmy.health.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    //passwordEncoder (required))
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(@NotNull HttpSecurity http) throws Exception {
        //if it's a login page then let the filter pass the request or can submit the form by front-end
        http
                .authorizeRequests()
                .antMatchers("/pages/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/pages/login.html").permitAll()
                .loginProcessingUrl("/pages/login.html")
                .defaultSuccessUrl("/pages/main.html").failureForwardUrl("/login.html")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().permitAll()
                .and().csrf().disable();
    }

    //choose the static file that you want to ignore
    @Override
    public void configure(@NotNull WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/**","/plugins/**","/template/","/img/**");
    }

}
