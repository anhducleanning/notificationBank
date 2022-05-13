package com.anhduc.noti_bank.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http
                 .cors().and()
                 .csrf().disable().authorizeRequests()
                 .antMatchers("/test").hasRole("ADMIN")
                 .anyRequest().authenticated()
                 .and()
                 .formLogin();
    }
}
