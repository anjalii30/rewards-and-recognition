package com.rar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "nominations")
public class Nominations implements Serializable {


    @Id
    @Column(name = "user_id")
    private long uid;


    @Column(name = "reward_id")
    private long rewardId;

    @Column (name="disable")
    private boolean disable=false;

//
//
//    //Nominationscriterias
//
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE,
//            },
//            mappedBy = "nominations")
//    private Set<Criterias> criterias2 = new HashSet<>();
//


    public Nominations() {
    }

    public Nominations(long uid, long rewardId) {
        this.uid = uid;
        this.rewardId = rewardId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getRewardId() {
        return rewardId;
    }

    public void setRewardId(long rewardId) {
        this.rewardId = rewardId;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
//
//    public Set<Criterias> getCriterias2() {
//        return criterias2;
//    }
//
//    public void setCriterias2(Set<Criterias> criterias2) {
//        this.criterias2 = criterias2;
//    }

    @Override
    public String toString() {
        return "Nominations{" +
//                "nominations_id=" + nominations_id +
                ", uid=" + uid +
                ", rewardId=" + rewardId +
                '}';
    }
}
