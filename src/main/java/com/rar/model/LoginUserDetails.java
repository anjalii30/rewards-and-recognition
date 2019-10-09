package com.rar.model;

import java.io.Serializable;

public class LoginUserDetails implements Serializable {

    private String email;
    private String name;
    private String imageUrl;
    private String generatedToken;

    public LoginUserDetails(String email, String name, String imageUrl, String generatedToken) {
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
        this.generatedToken = generatedToken;
    }

    public LoginUserDetails() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGeneratedToken() {
        return generatedToken;
    }

    public void setGeneratedToken(String generatedToken) {
        this.generatedToken = generatedToken;
    }
}
