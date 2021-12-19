package com.online.retailer.controller;

import com.online.retailer.Util.UserInfoUtil;
import com.online.retailer.mapper.IdentityMapper;
import com.online.retailer.model.Identity;
import com.online.retailer.model.MyUser;
import com.online.retailer.service.UserDetailIm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RegisterController{
    @Autowired
    IdentityMapper identityMapper;

    @Autowired
    UserDetailIm userDetailIm;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserInfoUtil userInfoUtil;
    @GetMapping("/register")
    public String register(){
        return "schoolmasterWNB/sign-up.html";
    }
    @PostMapping("/registerTwo")
    public String registerTwo(){
        return "schoolmasterWNB/sign-up-two.html";
    }
    @PostMapping("/registerThree")
    public String registerThree(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        MyUser myUser = userInfoUtil.getUserInfoByUsername(username);
        myUser.setUsername(username);
        myUser.setPassword(password);
        myUser.setId(0);
        myUser.setName("");
        List<Identity> identityList = myUser.getIdentityList();

        //进行身份验证
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(myUser.getUsername(),myUser.getPassword());
        try{
            token.setDetails(new WebAuthenticationDetails(request));
            Authentication authenticatedUser = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
//            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//            authenticationManagerBuilder.userDetailsService(userDetailIm).passwordEncoder(bCryptPasswordEncoder);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return "schoolmasterWNB/sign-up-last.html";
    }
}
