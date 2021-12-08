package com.online.retailer.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class Manager {
    private int managerId;
    private String managerUsername;
    private String managerPassword;
    private String managerIdentity;
    private String managerName;
    private String managerReallyName;
    private String managerPhone;
    private String managerIDCard;
    public Manager(){}
    public Manager(int managerId, String managerUsername, String managerPassword, String managerIdentity, String managerName, String managerReallyName, String managerPhone, String managerIDCard) {
        this.managerId = managerId;
        this.managerUsername = managerUsername;
        this.managerPassword = managerPassword;
        this.managerIdentity = managerIdentity;
        this.managerName = managerName;
        this.managerReallyName = managerReallyName;
        this.managerPhone = managerPhone;
        this.managerIDCard = managerIDCard;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", managerUsername='" + managerUsername + '\'' +
                ", managerPassword='" + managerPassword + '\'' +
                ", managerIdentity='" + managerIdentity + '\'' +
                ", managerName='" + managerName + '\'' +
                ", managerReallyName='" + managerReallyName + '\'' +
                ", managerPhone='" + managerPhone + '\'' +
                ", managerIDCard='" + managerIDCard + '\'' +
                '}';
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerReallyName() {
        return managerReallyName;
    }

    public void setManagerReallyName(String managerReallyName) {
        this.managerReallyName = managerReallyName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getManagerIDCard() {
        return managerIDCard;
    }

    public void setManagerIDCard(String managerIDCard) {
        this.managerIDCard = managerIDCard;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String getManagerIdentity() {
        return managerIdentity;
    }

    public void setManagerIdentity(String managerIdentity) {
        this.managerIdentity = managerIdentity;
    }
}
