package com.rar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "DTO for user nominations")
public class UserNominationDetails implements Serializable {

    @ApiModelProperty(value = "The id of user")
    private long userId;

    @ApiModelProperty(value = "The reason for nomination")
    private String reason;

    @ApiModelProperty(value = "The name of the user")
    private String userName;

    @ApiModelProperty(value = "boolean for whether the user is selected or not")
    private Boolean selected;

    public UserNominationDetails(long userId, String reason, String userName, Boolean selected) {
        this.userId = userId;
        this.reason = reason;
        this.userName = userName;
        this.selected = selected;
    }

    public UserNominationDetails() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
