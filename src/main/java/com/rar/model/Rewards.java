package com.rar.model;

import com.rar.enums.CategoryEnum;
import com.rar.enums.FrequencyEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.rar.utils.Constants.CREATED;

@Entity
@Table(name="rewards")
@ApiModel(description = "All the details of a reward")
public class Rewards implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_id",unique = true,nullable = false)
    @ApiModelProperty(notes = "The database generated rewardID")
    private long rewardId;

    @Column(name="reward_name")
    @ApiModelProperty(notes = "The name of the reward")
    @NotEmpty
    private String rewardName;

    @Column(name = "coins")
    @ApiModelProperty(notes = "number of coins associated to a particular reward")
    private Long coins;

    @Column(name="frequency")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "The frequency of the reward")
    private FrequencyEnum frequency;

    @Column(name="description")
    @ApiModelProperty(notes = "The description of that particular reward")
    @NotEmpty
    private String description;

    @Column(name="regenerated")
    @ApiModelProperty(notes = "Used for regenerated rewards")
    private boolean regenerated=true;

    @Column(name="category")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Category of a particular reward")
    private CategoryEnum category;

    @Column(name="start_date")
    @ApiModelProperty(notes = "The start date of the project")
    private LocalDate startDate;

    @Column(name="end_date")
    @ApiModelProperty(notes = "The end date of the project")
    private LocalDate endDate;

    @Column(name="self_nominate")
    @ApiModelProperty(notes = "Used for self nominating rewards")
    private boolean selfNominate;

    @Column(name="nominations_allowed")
    @ApiModelProperty(notes = "The number of nominations allowed for reward")
    private int nominationsAllowed;

    @Column(name="award_status")
    @Max(value=5)
    @ApiModelProperty(notes = "The status of the reward")
    private int awardStatus =CREATED;

    @Column(name="discontinuing_date")
    @ApiModelProperty(notes = "The discontinuing date of reward")
    private Date discontinuingDate;

    @Column(name="discontinuing_reason")
    @ApiModelProperty(notes = "The reason for discontinuing a reward")
    private String discontinuingReason;

    @Column(name="roll_out_id")
    @ApiModelProperty(notes = "storing the reward id if the reward is edited in the roll-out checking")
    private long rollOutId=0;

    @OneToMany(
            mappedBy = "rewards",cascade = CascadeType.REFRESH
    )
    private List<RewardsCriteria> criteria = new ArrayList<>();

    @OneToMany(mappedBy = "rewards",cascade = CascadeType.ALL)
    private List<Nominations> nominations;

    public Rewards() {
        // empty constructor
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


    @Override
    public String toString() {
        return "Rewards{" +
                "rewardId=" + rewardId +
                ", reward_name='" + rewardName + '\'' +
                ", frequency=" + frequency +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", start_date=" + startDate +
                ", end_date=" + endDate +
                ", self_nominate=" + selfNominate +
                ", nominations_allowed=" + nominationsAllowed +
                ", award_status=" + awardStatus +
                ", discontinuingDate=" + discontinuingDate +
                ", discontinuingReason='" + discontinuingReason + '\'' +
                ", criteria=" + criteria +
                '}';
    }

}
