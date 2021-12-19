package com.online.retailer.controller;

import com.online.retailer.mapper.GoodsMapper;
import com.online.retailer.model.Goods;
import com.online.retailer.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class BackstageManageController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsMapper goodsMapper;

    @RequestMapping("/backstage")
    public String backstage() {
        return "schoolmasterWNB/backstageManage.html";
    }

    @RequestMapping("/insertGoods")
    public String insertGoods(HttpServletRequest request, Model model) {
        Goods goods = new Goods();
        goods.setGoodsName(request.getParameter("name"));
        goods.setGoodsDiscount(Float.valueOf(request.getParameter("discount")));
        goods.setGoodsProducer(request.getParameter("producer"));
        goods.setGoodsProduceDate(new Date());
        goods.setGoodsPrice(Integer.valueOf(request.getParameter("price")));
        goods.setGoodsImgUri(request.getParameter("imgUri"));
        goods.setGoodsIdentifier(request.getParameter("identifier"));
        goods.setGoodsExpire(new Date());
        goods.setGoodsNum(Integer.valueOf(request.getParameter("num")));
        boolean isInsert = goodsMapper.insertGoods(goods);
        if (isInsert) model.addAttribute("insertResult", "成功");
        else model.addAttribute("insertResult", "失败了");
        return "schoolmasterWNB/backstageManage.html";
    }

    @RequestMapping("/delGoods")
    public String delGoods(HttpServletRequest request, Model model) {
        boolean isDelete = goodsMapper.deleteGoodsById(Integer.valueOf(request.getParameter("id")));
        if (isDelete) model.addAttribute("delResult", "成功");
        else model.addAttribute("delResult", "失败了");
        return "schoolmasterWNB/backstageManage.html";
    }

    @RequestMapping("/updateGoods")
    public String updateGoods(HttpServletRequest request, Model model) {
        Goods goods = new Goods();
        goods.setGoodsId(Integer.valueOf(request.getParameter("id")));
        goods.setGoodsName(request.getParameter("name"));
        goods.setGoodsDiscount(Float.valueOf(request.getParameter("discount")));
        goods.setGoodsProducer(request.getParameter("producer"));
        goods.setGoodsProduceDate(new Date());
        goods.setGoodsPrice(Integer.valueOf(request.getParameter("price")));
        goods.setGoodsImgUri(request.getParameter("imgUri"));
        goods.setGoodsIdentifier(request.getParameter("identifier"));
        goods.setGoodsExpire(new Date());
        goods.setGoodsNum(Integer.valueOf(request.getParameter("num")));
        boolean isUpdate = goodsMapper.updateGoodsById(goods);
        if (isUpdate) model.addAttribute("updateResult", "成功");
        else model.addAttribute("updateResult", "失败了");
        return "schoolmasterWNB/backstageManage.html";
    }

    @RequestMapping("/selectGoods")
    public String selectGoods(HttpServletRequest request, Model model) {
        List<Goods> goodsList = goodsMapper.selectGoodsesByName(request.getParameter("name"));
//        boolean isInsert = goodsMapper.insertGoods(goods);
        if (goodsList != null) model.addAttribute("GoodsResult", goodsList);
        else model.addAttribute("GoodsResult", "没有此商品的内容");
        return "schoolmasterWNB/backstageManage.html";
    }
}
