package com.online.retailer.controller;

import com.online.retailer.annotation.CheckToken;
import com.online.retailer.mapper.CustomerMapper;
import com.online.retailer.model.Customer;
import com.online.retailer.model.MyUser;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class OnlineChatController {
    @Autowired
    CustomerMapper customerMapper;
    @RequestMapping("/getUserInfo")
    @ResponseBody
    @CheckToken
    public String getUserInfo(HttpSession session){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JSONObject jsonObject = JSONObject.fromObject(principal);
        return jsonObject.toString();
    }

    @RequestMapping("/getCustomerInfo")
    @ResponseBody
    @CheckToken
    public String getCustomerInfo(){
        Customer customer = new Customer();
        customer = customerMapper.selectCustomerByAccount(SecurityContextHolder.getContext().getAuthentication().getName());
        customer.setCustomerPassword("[PROTECT]");
        return JSONObject.fromObject(customer).toString();
    }

    @RequestMapping("/chat")
    public String chat(){
        return "schoolmasterWNB/socket.html";
    }
}
