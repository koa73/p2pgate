package com.p2pgate.bank_gate.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/assets/**",  "/pay/**").permitAll()
                .anyRequest().hasAuthority("WEB_USER")
                .and()
        ;

        http.headers().cacheControl();
    }
}


