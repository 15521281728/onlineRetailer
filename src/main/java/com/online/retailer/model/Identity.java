package com.online.retailer.model;

import org.springframework.stereotype.Component;

@Component
public class Identity {
    int id;
    String identity;

    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                ", identity='" + identity + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
