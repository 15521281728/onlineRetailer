package com.online.retailer.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Component
public class Customer implements IUser {
    //基本对应数据库的键
    //实现IUser的接口
    private int userId;
    private String userName;
    private String userAccount;
    private int userPassword;
    private int userWallet;
    private String userAddress;
    private String userIdentity;
    private Date userBirthday;
    private String userBankCard;
    private String userBank;
    private String userGender;

    @Override
    public String toString() {
        return "Customer{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword=" + userPassword +
                ", userWallet=" + userWallet +
                ", userAddress='" + userAddress + '\'' +
                ", userIdentity='" + userIdentity + '\'' +
                ", userBirthday=" + userBirthday +
                ", userBankCard='" + userBankCard + '\'' +
                ", userBank='" + userBank + '\'' +
                ", userGender='" + userGender + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public int getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(int userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(int userWallet) {
        this.userWallet = userWallet;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserBankCard() {
        return userBankCard;
    }

    public void setUserBankCard(String userBankCard) {
        this.userBankCard = userBankCard;
    }

    public String getUserBank() {
        return userBank;
    }

    public void setUserBank(String userBank) {
        this.userBank = userBank;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
}
