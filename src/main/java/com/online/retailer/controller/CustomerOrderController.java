package com.online.retailer.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerOrderController {
    @RequestMapping("/myOrder")
    public String myOrder(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "schoolmasterWNB/myorder.html";
    }
}
