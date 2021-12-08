package com.online.retailer.config;

import com.online.retailer.service.UserDetailIm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailIm userDetailIm;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailIm).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/index.html").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register","/registerTwo","/registerThree").permitAll()
                .antMatchers("/css/**","/js/**","/images/**","pxcp/**").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .formLogin();
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("userAccount").passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/login?error");

//        http.logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/");
    }
}
