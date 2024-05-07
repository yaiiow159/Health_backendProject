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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    //passwordEncoder (required))
    @Bean("passwordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(@NotNull HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/security/**").permitAll()
                .antMatchers("/pages/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/pages/login.html").permitAll()
                .loginProcessingUrl("/security/login").permitAll()
                .defaultSuccessUrl("/pages/main.html", true)
                .failureForwardUrl("/pages/login.html")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/security/logout")
                .logoutSuccessUrl("/pages/login.html")
                .clearAuthentication(true)
                .invalidateHttpSession(true).permitAll().and().csrf().disable();

        http.headers().frameOptions().sameOrigin();
    }


    //choose the static file that you want to ignore
    @Override
    public void configure(@NotNull WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/pages/login.html",
                "/js/**",
                "/css/**",
                "/plugins/**",
                "/template/*",
                "/img/**");
    }

}
