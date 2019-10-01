package com.rar.model;

import com.rar.enums.CategoryEnum;
import com.rar.enums.FrequencyEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="rewards")
public class Rewards implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_id",unique = true,nullable = false)
    private long rewardId;

    @Column
    private String reward_name;

    @Column
    @Enumerated(EnumType.STRING)
    private FrequencyEnum frequency;

    @Column
    private String description;

    @Column
    private boolean regenerated=true;


    @Column
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column
    private LocalDate start_date;

    @Column
    private LocalDate end_date;


    @Column
    private boolean self_nominate;

    @Column
    private int nominations_allowed;

    @Column
    private int award_status = 0;

    @Column
    private Date discontinuingDate;

    @Column
    private String discontinuingReason;


    @OneToMany(
            mappedBy = "rewards"
    )
    private List<RewardsCriterias> criterias = new ArrayList<>();


/*
    //CRITERIAS

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "rewards_criterias",
            joinColumns = {@JoinColumn(name = "Reward_Id")},
            inverseJoinColumns = {@JoinColumn(name = "Criterias_Id")}

    )
    private Set<RewardsCriterias> criterias= new HashSet<>();*/
//    private Set<Criterias> criterias = new HashSet<>();


   // private Set<RewardsCriterias> rewardsCriterias= new HashSet<>();


    //nominations



    public Rewards() {
    }


    public Rewards(long rewardId, String reward_name, FrequencyEnum frequency, String description, boolean regenerated, CategoryEnum category, LocalDate start_date, LocalDate end_date, boolean self_nominate, int nominations_allowed, int award_status, Date discontinuingDate, String discontinuingReason, List<RewardsCriterias> criterias, Set<UserInfo> userInfo4) {
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
        this.criterias = criterias;
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

    public List<RewardsCriterias> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<RewardsCriterias> criterias) {
        this.criterias = criterias;
    }


    //    public Set<Criterias> getCriterias() {
//        return criterias;
//    }
//
//    public void setCriterias(Set<Criterias> criterias) {
//        this.criterias = criterias;
//    }


//
//    public Set<RewardsCriterias> getRewardsCriterias() {
//        return rewardsCriterias;
//    }
//
//    public void setRewardsCriterias(Set<RewardsCriterias> rewardsCriterias) {
//        this.rewardsCriterias = rewardsCriterias;
//    }

/*

    public Set<RewardsCriterias> getCriterias() {
        return criterias;
    }

    public void setCriterias(Set<RewardsCriterias> criterias) {
        this.criterias = criterias;
    }
*/

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
                ", criterias=" + criterias +
                '}';
    }

   /* public void add(Tag tag) {
        PostTag postTag = new PostTag(this, tag);
        tags.add(postTag);
        tag.getPosts().add(postTag);*/
//    }







}
