package com.rar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rar.dto.RewardsCriteriaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@IdClass(RewardsCriteriaId.class)
@Table(name = "rewards_criteria")
@ApiModel(description = "Mapping of rewards with criteria")
public class RewardsCriteria implements Serializable {

    @Id
    @Column(name = "reward_id")
    @ApiModelProperty(notes = "The ID of Reward")
    private long rewardId;

    @Id
    @Column(name = "criteria_id")
    @ApiModelProperty(notes = "The ID of Criteria")
    private long criteriaId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "reward_id", referencedColumnName = "reward_id", insertable = false, updatable = false)
    })
    @JsonIgnore
    private Rewards rewards;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "criteria_id", referencedColumnName = "criteria_id", insertable = false, updatable = false)
    })
    @JsonIgnore
    private Criteria criteria;

    @Column(name = "isCompulsory")
    private Boolean isCompulsory;


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

    public Rewards getRewards() {
        return rewards;
    }

    public void setRewards(Rewards rewards) {
        this.rewards = rewards;
    }

    public Criteria getCriteriaDesc() {
        return criteria;
    }

    public void setCriteriaDesc(Criteria criteria) {
        this.criteria = criteria;
    }

    public Boolean getCompulsory() {
        return isCompulsory;
    }

    public void setCompulsory(Boolean compulsory) {
        isCompulsory = compulsory;
    }


}
