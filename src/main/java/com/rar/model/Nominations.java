package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "nominations")
@ApiModel(description = "All the details required for a nomination")
public class Nominations implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nomination_id")
    @ApiModelProperty(notes = "The database generated nomination ID")
    private Long nominationID;

//    @Column(name = "project_id",length = 1000000000)
//    @ApiModelProperty(notes = "The id of the project in which the employee is working and wants to be nominated")
//    private Long project_id;

    @Column(name = "project_name")
    @ApiModelProperty(notes = "The name of the project")
    private String project_name;

    @Column(name = "reward_id")
    @ApiModelProperty(notes = "The ID of that particular reward")
    private Long rewardID;

    @Column(name = "reward_name")
    @ApiModelProperty(notes = "The name of the Reward")
    private String reward_name;

    @ApiModelProperty(notes ="Stating the reason for nomination")
    @Column(name = "reason", length = 1000000000)
    private String reason;

    @Column(name = "user_id")
    @ApiModelProperty(notes = "The User Id of the employee")
    private Long userID;

    @Column(name = "UserName")
    @ApiModelProperty(notes = "The name of the employee")
    private String username;

    @Column(name = "selected")
    @ApiModelProperty(notes = "Used in self nominated rewards when a manager approves it")
    private boolean selected=false;

    @ApiModelProperty(notes = "Used for selecting awardee by HR")
    @Column(name="hr_selected")
    private boolean hr_selected=false;


    @OneToMany(mappedBy = "nominations", cascade = CascadeType.ALL)
    private List<Evidences> evidencesList;

    public Nominations() {
    }

    public Nominations(Long project_id, Long rewardID, String reason, Long userID, List<Evidences> evidencesList) {
       // this.project_id = project_id;
        this.rewardID = rewardID;
        this.reason = reason;
        this.userID = userID;
        this.evidencesList = evidencesList;
    }

    public Nominations(Long nominationID, Long project_id, Long rewardID, String reason, Long userID,  boolean selected, boolean hr_selected, List<Evidences> evidencesList) {
        this.nominationID = nominationID;
      //  this.project_id = project_id;
        this.rewardID = rewardID;
        this.reason = reason;
        this.userID = userID;
        this.selected = selected;
        this.hr_selected = hr_selected;
        this.evidencesList = evidencesList;
    }

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


    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

   // public Long getProject_id() {
      //  return project_id;
  //  }

   // public void setProject_id(Long project_id) {
      //  this.project_id = project_id;
   // }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public boolean isHr_selected() {
        return hr_selected;
    }

    public void setHr_selected(boolean hr_selected) {
        this.hr_selected = hr_selected;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public List<Evidences> getEvidencesList() {
        return evidencesList;
    }

    public void setEvidencesList(List<Evidences> evidencesList) {
        this.evidencesList = evidencesList;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getReward_name() {
        return reward_name;
    }

    public void setReward_name(String reward_name) {
        this.reward_name = reward_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}








