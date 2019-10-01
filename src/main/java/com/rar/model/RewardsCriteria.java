package com.rar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@IdClass(RewardsCriteriaId.class)
@Table(name = "rewards_criteria")
public class RewardsCriteria {

    @Id
    @Column(name = "reward_id")
    private long rewardId;
    @Id
    @Column(name = "criteria_id")
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
    private Criterias criteria;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @MapsId("Reward_Id")
//    private Rewards rewards;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @MapsId("Criterias_Id")
//    private Criterias criterias;

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

    public Criterias getCriterias() {
        return criteria;
    }

    public void setCriterias(Criterias criteria) {
        this.criteria = criteria;
    }

    public Boolean getCompulsory() {
        return isCompulsory;
    }

    public void setCompulsory(Boolean compulsory) {
        isCompulsory = compulsory;
    }

    //    public RewardsCriteria() {
//    }
//
//    public RewardsCriteria(Rewards rewards, Criterias criterias) {
//        this.rewards = rewards;
//        this.criterias = criterias;
//        this.id = new RewardsCriteriaId(rewards.getId(), criterias.getCriteriaId());
//    }
//
//    public RewardsCriteria(Rewards rewards, Criterias criterias, Boolean isCompulsory) {
//        this.rewards = rewards;
//        this.criterias = criterias;
//        this.id = new RewardsCriteriaId(rewards.getId(), criterias.getCriteriaId());
//        this.isCompulsory = isCompulsory;
//
//    }

    /*
    public RewardsCriteria(Rewards rewards){
        this.id=new RewardsCriteriaId(rewards.getId(),rewards.getCriterias().iterator();
    }*/
//
//    public RewardsCriteriaId getId() {
//        return id;
//    }
//
//    public void setId(RewardsCriteriaId id) {
//        this.id = id;
//    }
//    @Transient
//    public Rewards getRewards() {
//        return rewards;
//    }
//
//    public void setRewards(Rewards rewards) {
//        this.rewards = rewards;
//    }
//    @Transient
//    public Criterias getCriterias() {
//        return criterias;
//    }
//
//    public void setCriterias(Criterias criterias) {
//        this.criterias = criterias;
//    }
//
//    public Boolean getCompulsory() {
//        return isCompulsory;
//    }
//
//    public void setCompulsory(Boolean compulsory) {
//        isCompulsory = compulsory;
//    }
//
//    @Override
//    public String toString() {
//        return "RewardsCriteria{" +
//                "id=" + id +
//                ", rewards=" + rewards +
//                ", criterias=" + criterias +
//                ", isCompulsory=" + isCompulsory +
//                '}';
//    }
//
//
///*    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//
//        if (o == null || getClass() != o.getClass())
//            return false;
//
//        RewardsCriteria that = (RewardsCriteria) o;
//        return Objects.equals(rewards.getId(), that.rewards.getId()) &&
//                Objects.equals(criterias.getCriteriaId(), that.criterias.getCriteriaId())&&
//                Objects.equals(isCompulsory, that.isCompulsory);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(rewards.getId(), criterias.getCriteriaId(),isCompulsory);
//    }*/
//
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//
//        RewardsCriteria that = (RewardsCriteria) o;
//
//        if (getId() != null ? !getId().equals(that.getId())
//                : that.getId() != null)
//            return false;
//
//        return true;
//    }
//
//    public int hashCode() {
//        return (getId() != null ? getId().hashCode() : 0);
//    }
}
