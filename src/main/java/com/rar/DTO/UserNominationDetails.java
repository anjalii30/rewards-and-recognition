package com.rar.DTO;

import java.io.Serializable;

public class UserNominationDetails implements Serializable {

    private long userId;

    private String reason;

    private String userName;

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
