package com.online.retailer.model;

import org.springframework.stereotype.Component;

@Component
public class Identity {
    int identityId;
    String identityRole;

    @Override
    public String toString() {
        return "Identity{" +
                "identityId=" + identityId +
                ", identityRole='" + identityRole + '\'' +
                '}';
    }

    public int getIdentityId() {
        return identityId;
    }

    public void setIdentityId(int identityId) {
        this.identityId = identityId;
    }

    public String getIdentityRole() {
        return identityRole;
    }

    public void setIdentityRole(String identityRose) {
        this.identityRole = identityRose;
    }
}
