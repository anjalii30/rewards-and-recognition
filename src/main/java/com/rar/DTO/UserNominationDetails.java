package com.rar.DTO;

import java.io.Serializable;

public class UserNominationDetails implements Serializable {

    private long userId;

    private String reason;

    public UserNominationDetails(long userId, String reason) {
        this.userId = userId;
        this.reason = reason;
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
}
