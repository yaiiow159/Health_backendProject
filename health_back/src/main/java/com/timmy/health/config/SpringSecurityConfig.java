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
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(@NotNull HttpSecurity http) throws Exception {
        // the overridden method is to check and set the http filter rules
        http
                .authorizeRequests().antMatchers("/pages/**").authenticated()
                .anyRequest().authenticated() //need to authenticate
                .and()
                .formLogin()
                .loginPage("/pages/login.html").permitAll()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/pages/main.html").failureForwardUrl("/pages/login.html")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().permitAll().invalidateHttpSession(true).logoutSuccessUrl("/pages/login.html")
                .and().csrf().disable();

        http.headers().frameOptions().sameOrigin(); //enable to use the iframe
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
