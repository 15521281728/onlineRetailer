package com.online.retailer.controller;

import com.online.retailer.Util.UserInfoUtil;
import com.online.retailer.mapper.GoodsMapper;
import com.online.retailer.service.GoodsService;
//import com.sun.tools.javac.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;

@Controller
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserInfoUtil userInfoUtil;
    @RequestMapping("/goodsPage")
    public RedirectView goodsPage(HttpServletRequest request, HttpServletResponse response){
        int goodsId;
        String goodsName;
        float goodsDiscount;
        String goodsIdentifier;
        String goodsImgUri;
        Date goodsExpire;
        Date goodsProduceDate;
        String goodsProducer;
        //差个goods原料
        int goodsNum;
        Cookie cookie = new Cookie("test","b");
        response.addCookie(cookie);
        String Uri = "http://localhost:8080/schoolmasterWNB/goodsPage.html?goodsId=123456&goodsName=mouse&goodsIdentifier=202199999&goodsUri=images/1.jpg&goodsExpire=2025&goodsProduceDate=2020&goodsProducer=China&goodsNum=9";
//        ModelAndView mv = new ModelAndView(Uri);
//        return mv;
//        return "redirect:schoolmasterWNB/goodsPage.html?goodsId=123456&goodsName=鼠标&goodsIdentifier=202199999&goodsUri=images/1.jpg&goodsExpire" +
//                "=2025&goodsProduceDate=2020&goodsProducer=中国&goodsNum=9";
//        response.encodeRedirectURL(Uri);
        return new RedirectView(Uri);
    }
//
    @RequestMapping("/buyGoods")
    public String buyGoods(HttpServletRequest request,HttpServletResponse response){
        //如果调用的类用到了spring框架，则在调用此类时，不应该手动new一个实例，而应该交给spring的@autowired来自动注入。
        //原理也很好懂，是因为自己new出来的实例，是不被算作spring的东西的，里面的@autowired就不起效果了，因为都不是spring的了
//        GoodsService goodsService = new GoodsService();
        try {
            int goodsId = Integer.valueOf(request.getParameter("goods_id"));
//            int goodsPrice = Integer.valueOf(request.getParameter("goods_price"));
            int customerId = userInfoUtil.getUserInfoByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
            int turnover = Integer.valueOf(request.getParameter("turnover"));

            boolean isBuy = goodsService.buyGoods(goodsId,customerId,turnover);
            if (isBuy){
                goodsMapper.insertGoods(goodsMapper.selectGoodsById(goodsId));
                return "index.html";
            }
            else{
                return "index.html?error";
            }

        }catch (Exception e){
            System.out.println("买商品时出错误了,错误是："+e);
            return "index.html";
        }
    }
}
