package com.rar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nominations")
public class Nominations implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nomination_id")//, updatable = false, nullable = false)
    private Long nominationID;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "reward_id")
    private long rewardID;
    @Column(name = "User_id")
    private Long userID;
    @Column(name = "frequency")
    private String frequency;
    @Column(name = "starting_date")
    private Date startingDate;
    @Column(name = "ending_date")
    private Date endingDate;

    @OneToMany(mappedBy = "nominations", cascade = CascadeType.ALL)
    private
    List<Evidences> evidencesList;

    public Long getNominationID() {
        return nominationID;
    }

    public void setNominationID(Long nominationID) {
        this.nominationID = nominationID;
    }


    public long getRewardID() {
        return rewardID;
    }

    public void setRewardID(long rewardID) {
        this.rewardID = rewardID;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public List<Evidences> getEvidencesList() {
        return evidencesList;
    }

    public void setEvidencesList(List<Evidences> evidencesList) {
        this.evidencesList = evidencesList;
    }
}








