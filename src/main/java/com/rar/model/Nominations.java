package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "nominations")
@ApiModel(description = "All the details requrired for a nomination")
public class Nominations implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nomination_id")
    @ApiModelProperty(notes = "The database generated nomination ID")
    private Long nominationID;

    @Column(name = "project_name",length = 1000000000)
    @ApiModelProperty(notes = "The name of the project in which the employee is working and wants to be nominated")
    private String project_name;

    @Column(name = "reward_id")
    @ApiModelProperty(notes = "The ID of that particular reward")
    private Long rewardID;

    @Column(name = "reward_name", length = 100000000)
    @ApiModelProperty(notes = "The name of the reward for which the employee is nominating")
    private String reward_name;

    public String getReward_name() {
        return reward_name;
    }

    public void setReward_name(String reward_name) {
        this.reward_name = reward_name;
    }

    @Column(name = "user_id")
    @ApiModelProperty(notes = "The User Id of the employee")
    private Long userID;

    @Column(name = "employee_name")
    @ApiModelProperty(notes = "The name of the employee getting nominated")
    private String employee_name;

    @Column(name = "selected")
    @ApiModelProperty(notes = "Used in self nominated rewards when a manager approves it")
    private boolean selected=false;

    @ApiModelProperty(notes = "Used for rejecting multiple nominations")
    @Column(name="disable")
    private boolean disable=false;

    @ApiModelProperty(notes ="Stating the reason for nomination")
    @Column(name = "reason", length = 1000000000)
    private String reason;

    @OneToMany(mappedBy = "nominations", cascade = CascadeType.ALL)
    private List<Evidences> evidencesList;

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

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

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








