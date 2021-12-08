package com.online.retailer.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Component
public class Customer {
    //基本对应数据库的键
    //实现IUser的接口
    private int CustomerId;
    private String CustomerName;
    private String CustomerAccount;
    private String CustomerPassword;
    private int CustomerWallet;
    private String CustomerAddress;
    private String CustomerIdentity;
    private Date CustomerBirthday;
    private String CustomerBankCard;
    private String CustomerBank;
    private String CustomerGender;

    @Override
    public String toString() {
        return "Customer{" +
                "CustomerId=" + CustomerId +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerAccount='" + CustomerAccount + '\'' +
                ", CustomerPassword=" + CustomerPassword +
                ", CustomerWallet=" + CustomerWallet +
                ", CustomerAddress='" + CustomerAddress + '\'' +
                ", CustomerIdentity='" + CustomerIdentity + '\'' +
                ", CustomerBirthday=" + CustomerBirthday +
                ", CustomerBankCard='" + CustomerBankCard + '\'' +
                ", CustomerBank='" + CustomerBank + '\'' +
                ", CustomerGender='" + CustomerGender + '\'' +
                '}';
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getCustomerAccount() {
        return CustomerAccount;
    }

    public void setCustomerAccount(String CustomerAccount) {
        this.CustomerAccount = CustomerAccount;
    }

    public String getCustomerPassword() {
        return CustomerPassword;
    }

    public void setCustomerPassword(String CustomerPassword) {
        this.CustomerPassword = CustomerPassword;
    }

    public int getCustomerWallet() {
        return CustomerWallet;
    }

    public void setCustomerWallet(int CustomerWallet) {
        this.CustomerWallet = CustomerWallet;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String CustomerAddress) {
        this.CustomerAddress = CustomerAddress;
    }

    public String getCustomerIdentity() {
        return CustomerIdentity;
    }

    public void setCustomerIdentity(String CustomerIdentity) {
        this.CustomerIdentity = CustomerIdentity;
    }

    public Date getCustomerBirthday() {
        return CustomerBirthday;
    }

    public void setCustomerBirthday(Date CustomerBirthday) {
        this.CustomerBirthday = CustomerBirthday;
    }

    public String getCustomerBankCard() {
        return CustomerBankCard;
    }

    public void setCustomerBankCard(String CustomerBankCard) {
        this.CustomerBankCard = CustomerBankCard;
    }

    public String getCustomerBank() {
        return CustomerBank;
    }

    public void setCustomerBank(String CustomerBank) {
        this.CustomerBank = CustomerBank;
    }

    public String getCustomerGender() {
        return CustomerGender;
    }

    public void setCustomerGender(String CustomerGender) {
        this.CustomerGender = CustomerGender;
    }
}
