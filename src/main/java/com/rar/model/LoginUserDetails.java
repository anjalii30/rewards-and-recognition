package com.rar.model;

import com.rar.enums.DesignationEnum;
import com.rar.enums.RoleEnum;

import java.io.Serializable;

public class LoginUserDetails implements Serializable {

    private String email;
    private String name;
    private String imageUrl;
    private String generatedToken;
    private RoleEnum roleEnum;
    private DesignationEnum designationEnum;

    public LoginUserDetails(String email, String name, String imageUrl, String generatedToken, RoleEnum roleEnum, DesignationEnum designationEnum) {
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
        this.generatedToken = generatedToken;
        this.roleEnum = roleEnum;
        this.designationEnum = designationEnum;
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

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public DesignationEnum getDesignationEnum() {
        return designationEnum;
    }

    public void setDesignationEnum(DesignationEnum designationEnum) {
        this.designationEnum = designationEnum;
    }
}
