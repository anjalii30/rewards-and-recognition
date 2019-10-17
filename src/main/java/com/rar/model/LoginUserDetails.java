package com.rar.model;

import com.rar.enums.DesignationEnum;
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
    @ApiModelProperty(notes = "The Image URL of the User")
    private String imageUrl;
    @ApiModelProperty(notes = "The generated token of the User")
    private String generatedToken;
    @ApiModelProperty(notes = "The role of the user")
    private RoleEnum roleEnum;
    @ApiModelProperty(notes = "The designation of the User")
    private DesignationEnum designationEnum;
    @ApiModelProperty(notes = "The id of the User")
    private Long uid;


    public LoginUserDetails(String email, String name, String imageUrl, String generatedToken, RoleEnum roleEnum, DesignationEnum designationEnum, Long uid) {
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
        this.generatedToken = generatedToken;
        this.roleEnum = roleEnum;
        this.designationEnum = designationEnum;
        this.uid = uid;
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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
