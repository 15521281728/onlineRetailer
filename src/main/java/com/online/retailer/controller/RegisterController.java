package com.online.retailer.controller;

import com.online.retailer.mapper.IdentityMapper;
import com.online.retailer.model.Identity;
import com.online.retailer.service.UserDetailIm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterController implements IController{
    @Autowired
    IdentityMapper identityMapper;

    @Autowired
    UserDetailIm userDetailIm;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @GetMapping("/register")
    public String register(){
        return "schoolmasterWNB/sign-up.html";
    }
    @PostMapping("/registerTwo")
    public String registerTwo(){
        return "schoolmasterWNB/sign-up-two.html";
    }
    @PostMapping("/registerThree")
    public String registerThree(HttpServletRequest httpServletRequest){
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        List<Identity> identityList = identityMapper.findIdentityByAccount(username);

        //进行身份验证
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
        try{
//            token.setDetails(userDetailIm);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            authenticationManagerBuilder.userDetailsService(userDetailIm).passwordEncoder(bCryptPasswordEncoder);
        }catch (AuthenticationException e){

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "schoolmasterWNB/sign-up-last.html";
    }
}
