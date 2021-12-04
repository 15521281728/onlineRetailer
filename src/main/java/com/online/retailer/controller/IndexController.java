package com.online.retailer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {
    //将商城主页的商品图片补充一下
    @GetMapping("/schoolmasterWNB/myorder")
    public String myorder(){
        return "schoolmasterWNB/myorder.html";
    }

}
