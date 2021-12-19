package com.online.retailer.config;

import com.online.retailer.handler.JwtLoginSuccessHandler;
import com.online.retailer.service.InvalidSessionService;
import com.online.retailer.service.UserDetailIm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailIm userDetailIm;

    @Autowired
    InvalidSessionService invalidSessionService;
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailIm).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //页面访问详情
        http.authorizeRequests()
                .antMatchers("/","/index.html").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register","/registerTwo","/registerThree").permitAll()
                .antMatchers("/goodsPage").permitAll()
                .antMatchers("/css/**","/js/**","/images/**","pxcp/**").permitAll()
                .antMatchers("/buyGoods").hasAnyRole("customer","superVIP")
                .antMatchers("/backstage").hasRole("manager")
                .anyRequest().authenticated();
//                .and()
//                .formLogin();
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/login?error")
                .successHandler(new JwtLoginSuccessHandler())
                ;

        //session超时处理
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .invalidSessionStrategy(invalidSessionService);
        //开启跨域访问
        http.cors()
                .disable();
//        http.logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
