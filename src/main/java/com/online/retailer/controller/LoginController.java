package com.online.retailer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginController implements IController{
    //实现登陆后，页面的请求
    @GetMapping("/login")
    public String myorder(){
//        System.out.println("跳转了");
        return "schoolmasterWNB/login.html";
    }
    //添加黑名单拦截器
    //通过security完成验证身份的操作
    //
}
