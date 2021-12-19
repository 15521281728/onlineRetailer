package com.online.retailer.controller;

import com.online.retailer.model.Identity;
import com.online.retailer.model.MyUser;
import com.online.retailer.Util.UserInfoUtil;
import com.online.retailer.Util.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class LoginController{
    @Autowired
    UserInfoUtil userInfoUtil;
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;
    //实现登陆后，页面的请求
    @GetMapping("/login")
    public String getLogin(Model model,HttpServletRequest request){
//        System.out.println("跳转了");
        model.addAttribute("text01","傻逼玩意儿");
        String currentUserName;
        int userId;
        List<Identity> identityList;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String refererUrl = request.getHeader("Referer");
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
//            userId = authentication.get
            Object principal = authentication.getPrincipal();
            Object details = authentication.getDetails();
            User user = (User)principal;
            System.out.println(user);
        }
        return "schoolmasterWNB/login.html";
    }

    @RequestMapping("/myLoginSuccessHandler")
    public void myOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pattern = ".*(/[a-z,A-Z]*)";
        String correctRedirectUrl = "";
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //获取原本打算重定向的url
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest saveRequest = requestCache.getRequest(request,response);
        String redirectUrl = saveRequest.getRedirectUrl();// 按指定模式在字符串查找

        //进行正则处理
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(redirectUrl);
        if (m.find()){
            correctRedirectUrl = m.group(1);
        }
        //创建并发送token至cookie
        MyUser myUser = userInfoUtil.getUserInfoByUsername(username);
        String token = JWTTokenUtil.createToken(myUser);
        Cookie cookie = new Cookie("myToken",token);
        response.addCookie(cookie);

        response.sendRedirect(correctRedirectUrl);
    }
//    @GetMapping("/login")
//    @ResponseBody
//    public String getLogin(HttpServletRequest request){
//        MyUser myUser = new MyUser();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
////            currentUserName = ;
////            userId = authentication.get
//            myUser.setUsername(authentication.getName());
//            Object principal = authentication.getPrincipal();
//            Object details = authentication.getDetails();
//            User user = (User)principal;
////            myUser.setIdentityList();
//        }
//        String token = JWTTokenService.createToken(myUser, 60 * 60 * 24);
//        return token;
//    }
//    @PostMapping("/login")
//    public String postLogin(){
////        System.out.println("跳转了");
//        return "schoolmasterWNB/login.html";
//    }
    //添加黑名单拦截器
    //通过security完成验证身份的操作
    //
}
