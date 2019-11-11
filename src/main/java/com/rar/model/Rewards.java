package com.rar.model;

import com.rar.enums.CategoryEnum;
import com.rar.enums.FrequencyEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="rewards")
@ApiModel(description = "All the details of a reward")
public class Rewards implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_id",unique = true,nullable = false)
    @ApiModelProperty(notes = "The database generated rewardID")
    private long rewardId;

    @Column
    @ApiModelProperty(notes = "The name of the reward")
    private String reward_name;

    @Column
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "The frequency of the reward")
    private FrequencyEnum frequency;

    @Column
    @ApiModelProperty(notes = "The description of that particular reward")
    private String description;

    @Column
    @ApiModelProperty(notes = "Used for regenerated rewards")
    private boolean regenerated=true;

    @Column
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Category of a particular reward")
    private CategoryEnum category;

    @Column
    @ApiModelProperty(notes = "The start date of the project")
    private LocalDate start_date;

    @Column
    @ApiModelProperty(notes = "The end date of the project")
    private LocalDate end_date;

    @Column
    @ApiModelProperty(notes = "Used for self nominating rewards")
    private boolean self_nominate;

    @Column
    @ApiModelProperty(notes = "The number of nominations allowed for reward")
    private int nominations_allowed;

    @Column
    @ApiModelProperty(notes = "The status of the reward")
    private int award_status = 0;

    @Column
    @ApiModelProperty(notes = "The discontinuing date of reward")
    private Date discontinuingDate;

    @Column
    @ApiModelProperty(notes = "The reason for discontinuing a reward")
    private String discontinuingReason;

    @OneToMany(
            mappedBy = "rewards",cascade = CascadeType.REFRESH
    )
    private List<RewardsCriteria> criteria = new ArrayList<>();

    public Rewards() {
    }

    public Rewards(long rewardId, String reward_name, FrequencyEnum frequency, String description, boolean regenerated, CategoryEnum category, LocalDate start_date, LocalDate end_date, boolean self_nominate, int nominations_allowed, int award_status, Date discontinuingDate, String discontinuingReason, List<RewardsCriteria> criteria, Set<UserInfo> userInfo4) {
        this.rewardId = rewardId;
        this.reward_name = reward_name;
        this.frequency = frequency;
        this.description = description;
        this.regenerated = regenerated;
        this.category = category;
        this.start_date = start_date;
        this.end_date = end_date;
        this.self_nominate = self_nominate;
        this.nominations_allowed = nominations_allowed;
        this.award_status = award_status;
        this.discontinuingDate = discontinuingDate;
        this.discontinuingReason = discontinuingReason;
        this.criteria = criteria;
    }

    public long getId() {
        return rewardId;
    }

    public void setId(long id) {
        this.rewardId = id;
    }

    public String getReward_name() {
        return reward_name;
    }

    public void setReward_name(String reward_name) {
        this.reward_name = reward_name;
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

    public LocalDate getStart_date() {
        return start_date;
    }

    public boolean isRegenerated() {
        return regenerated;
    }

    public void setRegenerated(boolean regenerated) {
        this.regenerated = regenerated;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }


    public boolean isSelf_nominate() {
        return self_nominate;
    }

    public void setSelf_nominate(boolean self_nominate) {
        this.self_nominate = self_nominate;
    }

    public int getNominations_allowed() {
        return nominations_allowed;
    }

    public void setNominations_allowed(int nominations_allowed) {
        this.nominations_allowed = nominations_allowed;
    }

    public int getAward_status() {
        return award_status;
    }

    public void setAward_status(int award_status) {
        this.award_status = award_status;
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

    public List<RewardsCriteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<RewardsCriteria> criteria) {
        this.criteria = criteria;
    }

    public long getRewardId() {
        return rewardId;
    }

    public void setRewardId(long rewardId) {
        this.rewardId = rewardId;
    }


    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Rewards{" +
                "rewardId=" + rewardId +
                ", reward_name='" + reward_name + '\'' +
                ", frequency=" + frequency +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", self_nominate=" + self_nominate +
                ", nominations_allowed=" + nominations_allowed +
                ", award_status=" + award_status +
                ", discontinuingDate=" + discontinuingDate +
                ", discontinuingReason='" + discontinuingReason + '\'' +
                ", criteria=" + criteria +
                '}';
    }

}
