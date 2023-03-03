package com.works.configs;

import com.works.services.AdminDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final PasswordEncoder passwordEncoder;
    final AdminDetailService adminDetailService;

    // sql -> role
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetailService).passwordEncoder(passwordEncoder);
    }

    // role -> mapping
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic()
        .and()
        .authorizeHttpRequests()
        .antMatchers("/product/**").hasRole("product")
        .antMatchers("/note/**").hasRole("note")
        .and()
        .csrf().disable().formLogin().disable();

    }

}


/*
ali@mail.com -> product
mehmet@mail.com -> note
zehra@mail.com -> product, note
 */