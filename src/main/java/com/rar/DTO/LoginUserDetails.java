package com.rar.DTO;

import com.rar.enums.RoleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "All the Login details of user")
public class LoginUserDetails implements Serializable {

    @ApiModelProperty(notes = "emailID of the user")
    private String email;

    @ApiModelProperty(notes = "Name of the user")
    private String name;

    @ApiModelProperty(notes = "The Image URL of the user")
    private String imageUrl;

    @ApiModelProperty(notes = "The generated jwt token of the user")
    private String jwtToken;

    @ApiModelProperty(notes = "The role of the user")
    private RoleEnum role;

    @ApiModelProperty(notes = "The designation of the User")
    private String designation;

    @ApiModelProperty(notes = "The id of the User")
    private Long userId;

    @ApiModelProperty(notes = "Stores whether the employee is manager or not")
    private Boolean isManager;

    @ApiModelProperty(notes = "The balance in the wallet of user")
    private double wallet;

    public LoginUserDetails(String email, String name, String imageUrl, String generatedToken, RoleEnum roleEnum, String designation, Long userId, Boolean isManager, double wallet) {
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
        this.jwtToken = generatedToken;
        this.role = roleEnum;
        this.designation = designation;
        this.userId = userId;
        this.isManager = isManager;
        this.wallet = wallet;
    }

    public LoginUserDetails() {
    }

    public LoginUserDetails(String s, String s1, String s2, String s3, RoleEnum roleEnum, Long id) {
    }

    public LoginUserDetails(String email, String name, String imageUrl, String generatedToken, RoleEnum roleEnum, String designation, Long id, boolean isManager, String wallet) {
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

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public LoginUserDetails(String email, String name, String imageUrl, Long userId) {
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
        this.userId = userId;
    }
}
