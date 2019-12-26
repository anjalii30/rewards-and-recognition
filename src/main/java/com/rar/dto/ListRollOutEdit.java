package com.rar.dto;

import com.rar.enums.FrequencyEnum;
import com.rar.model.RewardsCriteria;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class ListRollOutEdit implements Serializable {

    private long rewardId;
    private String rewardName;
    private Long coins;
    private FrequencyEnum frequency;
    private String description;
    private LocalDate endDate;
    private int nominationsAllowed;
    private List<RewardsCriteria> criteria;

    public ListRollOutEdit(long rewardId, String rewardName, Long coins, FrequencyEnum frequency, String description, LocalDate endDate, int nominationsAllowed, List<RewardsCriteria> criteria) {
        this.rewardId = rewardId;
        this.rewardName = rewardName;
        this.coins = coins;
        this.frequency = frequency;
        this.description = description;
        this.endDate = endDate;
        this.nominationsAllowed = nominationsAllowed;
        this.criteria = criteria;
    }

    public long getRewardId() {
        return rewardId;
    }

    public void setRewardId(long rewardId) {
        this.rewardId = rewardId;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public Long getCoins() {
        return coins;
    }

    public void setCoins(Long coins) {
        this.coins = coins;
    }

    public FrequencyEnum getFrequency() {
        return frequency;
    }

    public void setFrequency(FrequencyEnum frequency) {
        this.frequency = frequency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getNominationsAllowed() {
        return nominationsAllowed;
    }

    public void setNominationsAllowed(int nominationsAllowed) {
        this.nominationsAllowed = nominationsAllowed;
    }

    public List<RewardsCriteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<RewardsCriteria> criteria) {
        this.criteria = criteria;
    }


}
