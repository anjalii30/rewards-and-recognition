package com.rar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@ApiModel(description = "The DTO of rewards and criteria")
public class RewardsCriteriaId implements Serializable {

    @ApiModelProperty(notes = "The ID of reward")
    private long rewardId;

    @ApiModelProperty(notes = "The ID of criteria")
    private long criteriaId;

    public long getRewardId() {
        return rewardId;
    }

    public void setRewardId(long rewardId) {
        this.rewardId = rewardId;
    }

    public long getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(long criteriaId) {
        this.criteriaId = criteriaId;
    }
}
