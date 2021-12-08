package com.online.retailer.model;

import org.springframework.stereotype.Component;

import java.util.List;

public class MyUser {
    private int id;
    private String username;
    private String password;
    List<Identity> identityList;
    public MyUser(){

    }

    public MyUser(int id, String username, String password, List<Identity> identityList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.identityList = identityList;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", identityList=" + identityList +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Identity> getIdentityList() {
        return identityList;
    }

    public void setIdentityList(List<Identity> identityList) {
        this.identityList = identityList;
    }
}
