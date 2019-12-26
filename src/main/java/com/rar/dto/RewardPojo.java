package com.rar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "For update award status")
public class RewardPojo {

    @ApiModelProperty(notes = "The status of the reward")
    private int awardStatus;

    @ApiModelProperty(notes = "The discontinuing date of reward")
    private Date discontinuingDate;

    @ApiModelProperty(notes = "The reason for discontinuing a reward")
    private String discontinuingReason;

    public int getAwardStatus() {
        return awardStatus;
    }

    public void setAwardStatus(int awardStatus) {
        this.awardStatus = awardStatus;
    }

    public Date getDiscontinuingDate() {
        return discontinuingDate;
    }

    public void setDiscontinuingDate(Date discontinuingDate) {
        this.discontinuingDate = discontinuingDate;
    }

    public String getDiscontinuingReason() {
        return discontinuingReason;
    }

    public void setDiscontinuingReason(String discontinuingReason) {
        this.discontinuingReason = discontinuingReason;
    }
}
