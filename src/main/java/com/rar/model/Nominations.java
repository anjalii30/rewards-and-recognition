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
    private Long rewardID;
    //@Column(name = "reward_name")
    //private String reward_name;
    @Column(name = "User_id")
    private Long userID;
    @Column(name = "frequency")
    private String frequency;
    @Column(name = "start_date")
    private Date startingDate;
    @Column(name = "end_date")
    private Date endingDate;
    @Column(name = "employee_name")
    private String employee_name;
    @Column(name = "selected")
    private boolean selected=false;
    @Column(name="disable")
    private boolean disable=false;

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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

//    public String getReward_name() {
//        return reward_name;
//    }
//
//    public void setReward_name(String reward_name) {
//        this.reward_name = reward_name;
//    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public List<Evidences> getEvidencesList() {
        return evidencesList;
    }

    public void setEvidencesList(List<Evidences> evidencesList) {
        this.evidencesList = evidencesList;
    }

}








