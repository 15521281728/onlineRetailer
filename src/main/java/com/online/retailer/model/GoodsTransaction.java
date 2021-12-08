package com.online.retailer.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GoodsTransaction {
    private int goodsId;
    private int userId;
    private Date transactionTime;
    private int transactionPrice;
    private String transactionHarvestAddress;
    private int transactionId;
    private int transactionNum;

    @Override
    public String toString() {
        return "GoodsTransaction{" +
                "goodsId=" + goodsId +
                ", userId=" + userId +
                ", transactionTime=" + transactionTime +
                ", transactionPrice=" + transactionPrice +
                ", transactionHarvestAddress='" + transactionHarvestAddress + '\'' +
                ", transactionId=" + transactionId +
                ", transactionNum=" + transactionNum +
                '}';
    }

    public int getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(int transactionNum) {
        this.transactionNum = transactionNum;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public int getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(int transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public String getTransactionHarvestAddress() {
        return transactionHarvestAddress;
    }

    public void setTransactionHarvestAddress(String transactionHarvestAddress) {
        this.transactionHarvestAddress = transactionHarvestAddress;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
