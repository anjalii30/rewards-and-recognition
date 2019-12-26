package com.rar.dto;

import com.rar.enums.CategoryEnum;
import com.rar.enums.FrequencyEnum;
import com.rar.model.RewardsCriteria;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.rar.utils.Constants.CREATED;

public class RewardsDto implements Serializable {

    private long rewardId;
    private String rewardName;
    private Long coins;
    private FrequencyEnum frequency;
    private String description;
    private boolean regenerated=true;
    private CategoryEnum category;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean selfNominate;
    private int nominationsAllowed;
    private int awardStatus =CREATED;
    private Date discontinuingDate;
    private String discontinuingReason;
    private long rollOutId=0;
    private List<RewardsCriteria> criteria = new ArrayList<>();

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

    public boolean isRegenerated() {
        return regenerated;
    }

    public void setRegenerated(boolean regenerated) {
        this.regenerated = regenerated;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isSelfNominate() {
        return selfNominate;
    }

    public void setSelfNominate(boolean selfNominate) {
        this.selfNominate = selfNominate;
    }

    public int getNominationsAllowed() {
        return nominationsAllowed;
    }

    public void setNominationsAllowed(int nominationsAllowed) {
        this.nominationsAllowed = nominationsAllowed;
    }

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

    public long getRollOutId() {
        return rollOutId;
    }

    public void setRollOutId(long rollOutId) {
        this.rollOutId = rollOutId;
    }

    public List<RewardsCriteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<RewardsCriteria> criteria) {
        this.criteria = criteria;
    }
}
