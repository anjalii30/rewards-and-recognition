package com.rar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@IdClass(RewardsCriteriasId.class)
@Table(name = "rewards_criterias")
public class RewardsCriterias{

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
    Rewards rewards;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "criteria_id", referencedColumnName = "criteria_id", insertable = false, updatable = false)
    })
    @JsonIgnore
    Criterias criterias;

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
        return criterias;
    }

    public void setCriterias(Criterias criterias) {
        this.criterias = criterias;
    }

    public Boolean getCompulsory() {
        return isCompulsory;
    }

    public void setCompulsory(Boolean compulsory) {
        isCompulsory = compulsory;
    }

    //    public RewardsCriterias() {
//    }
//
//    public RewardsCriterias(Rewards rewards, Criterias criterias) {
//        this.rewards = rewards;
//        this.criterias = criterias;
//        this.id = new RewardsCriteriasId(rewards.getId(), criterias.getCriteriaId());
//    }
//
//    public RewardsCriterias(Rewards rewards, Criterias criterias, Boolean isCompulsory) {
//        this.rewards = rewards;
//        this.criterias = criterias;
//        this.id = new RewardsCriteriasId(rewards.getId(), criterias.getCriteriaId());
//        this.isCompulsory = isCompulsory;
//
//    }

    /*
    public RewardsCriterias(Rewards rewards){
        this.id=new RewardsCriteriasId(rewards.getId(),rewards.getCriterias().iterator();
    }*/
//
//    public RewardsCriteriasId getId() {
//        return id;
//    }
//
//    public void setId(RewardsCriteriasId id) {
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
//        return "RewardsCriterias{" +
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
//        RewardsCriterias that = (RewardsCriterias) o;
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
//        RewardsCriterias that = (RewardsCriterias) o;
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
