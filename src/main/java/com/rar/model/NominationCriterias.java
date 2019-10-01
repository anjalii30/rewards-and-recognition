package com.rar.model;//package com.signon.model;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//
//
//@Entity
//@Table(name = "nominations_criterias")
//public class NominationCriterias implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "nominationscriterias_id",unique = true,nullable = false)
//    private long ncid;
//
//    @Column(name = "Criterias_Id")
//    private long criteriaId;
//
//    @Column(name = "User_Id")
//    private long uid;
//
//    @Column(name = "Reward_Id")
//    private long rewardId;
//
//    @Column
//    private String criteriaDescrition;
//
//    @Column(length = 2147483000)
//    private String evidences;
//
//    public NominationCriterias() {
//    }
//
//    public NominationCriterias(long ncid, long criteriaId, long uid, long rewardId, String criteriaDescrition, String evidences) {
//        this.ncid = ncid;
//        this.criteriaId = criteriaId;
//        this.uid = uid;
//        this.rewardId = rewardId;
//        this.criteriaDescrition = criteriaDescrition;
//        this.evidences = evidences;
//    }
//
//    public long getNcid() {
//        return ncid;
//    }
//
//    public void setNcid(long ncid) {
//        this.ncid = ncid;
//    }
//
//    public long getCriteriaId() {
//        return criteriaId;
//    }
//
//    public void setCriteriaId(long criteriaId) {
//        this.criteriaId = criteriaId;
//    }
//
//    public long getUid() {
//        return uid;
//    }
//
//    public void setUid(long uid) {
//        this.uid = uid;
//    }
//
//    public long getRewardId() {
//        return rewardId;
//    }
//
//    public void setRewardId(long rewardId) {
//        this.rewardId = rewardId;
//    }
//
//    public String getCriteriaDescrition() {
//        return criteriaDescrition;
//    }
//
//    public void setCriteriaDescrition(String criteriaDescrition) {
//        this.criteriaDescrition = criteriaDescrition;
//    }
//
//    public String getEvidences() {
//        return evidences;
//    }
//
//    public void setEvidences(String evidences) {
//        this.evidences = evidences;
//    }
//
//    @Override
//    public String toString() {
//        return "NominationCriterias{" +
//                "ncid=" + ncid +
//                ", criteriaId=" + criteriaId +
//                ", uid=" + uid +
//                ", rewardId=" + rewardId +
//                ", criteriaDescrition='" + criteriaDescrition + '\'' +
//                ", evidences='" + evidences + '\'' +
//                '}';
//    }
//}
