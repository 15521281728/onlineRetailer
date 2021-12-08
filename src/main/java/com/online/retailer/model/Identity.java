package com.online.retailer.model;

import org.springframework.stereotype.Component;

@Component
public class Identity {
    int identityId;
    String identityRose;

    @Override
    public String toString() {
        return "Identity{" +
                "identityId=" + identityId +
                ", identityRose='" + identityRose + '\'' +
                '}';
    }

    public int getIdentityId() {
        return identityId;
    }

    public void setIdentityId(int identityId) {
        this.identityId = identityId;
    }

    public String getIdentityRose() {
        return identityRose;
    }

    public void setIdentityRose(String identityRose) {
        this.identityRose = identityRose;
    }
}
