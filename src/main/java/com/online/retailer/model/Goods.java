package com.online.retailer.model;

import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
public class Goods {
    //mysql商品基本信息
    int goodsPrice;
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

    @Override
    public String toString() {
        return "Goods{" +
                "goodsPrice="+goodsPrice+
                ",goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsDiscount=" + goodsDiscount +
                ", goodsIdentifier='" + goodsIdentifier + '\'' +
                ", goodsImgUri='" + goodsImgUri + '\'' +
                ", goodsExpire=" + goodsExpire +
                ", goodsProduceDate=" + goodsProduceDate +
                ", goodsFactory='" + goodsProducer + '\'' +
                ", goodsNum=" + goodsNum +
                '}';
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsProducer() {
        return goodsProducer;
    }

    public void setGoodsProducer(String goodsProducer) {
        this.goodsProducer = goodsProducer;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public float getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(float goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public String getGoodsIdentifier() {
        return goodsIdentifier;
    }

    public void setGoodsIdentifier(String goodsIdentifier) {
        this.goodsIdentifier = goodsIdentifier;
    }

    public String getGoodsImgUri() {
        return goodsImgUri;
    }

    public void setGoodsImgUri(String goodsImgUri) {
        this.goodsImgUri = goodsImgUri;
    }

    public Date getGoodsExpire() {
        return goodsExpire;
    }

    public void setGoodsExpire(Date goodsExpire) {
        this.goodsExpire = goodsExpire;
    }

    public Date getGoodsProduceDate() {
        return goodsProduceDate;
    }

    public void setGoodsProduceDate(Date goodsProduceDate) {
        this.goodsProduceDate = goodsProduceDate;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }
    //如果商品在短时间内被读取很多次，则将商品移存至redis，
}
