package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(name = "project_name")
    @ApiModelProperty(notes = "The name of the project")
    private String projectName;

    @Column(name = "reward_id")
    @ApiModelProperty(notes = "The ID of that particular reward")
    @NotNull
    private Long rewardID;

    @Column(name = "reward_name")
    @ApiModelProperty(notes = "The name of the Reward")
    private String rewardName;

    @ApiModelProperty(notes ="Stating the reason for nomination")
    @Column(name = "reason", length = 1000000000)
    private String reason;

    @Column(name = "user_id")
    @ApiModelProperty(notes = "The User Id of the employee")
    @NotNull
    private Long userID;

    @Column(name = "user_name")
    @ApiModelProperty(notes = "The name of the employee")
    private String userName;

    @Column(name = "selected")
    @ApiModelProperty(notes = "Used in self nominated rewards when a manager approves it")
    private boolean selected=false;

    @ApiModelProperty(notes = "Used for selecting awardee by HR")
    @Column(name="hr_selected")
    private boolean hrSelected=false;

    @ApiModelProperty(notes = "The ID of a particular project")
    @NotNull
    @Column(name = "project_id")
    private Long projectId;

    @ApiModelProperty(notes = "The ID of a the manager")
    @Column(name = "manager_id")
    @NotNull
    private Long managerId;

    @OneToMany(mappedBy = "nominations", cascade = CascadeType.ALL)
    private List<Evidences> evidencesList;

    public Nominations() {
    }

    public Nominations(Long rewardID, String reason, Long userID, List<Evidences> evidencesList) {
        this.rewardID = rewardID;
        this.reason = reason;
        this.userID = userID;
        this.evidencesList = evidencesList;
    }

    public Nominations(Long nominationID, Long rewardID, String reason, Long userID, boolean selected, boolean hrSelected, List<Evidences> evidencesList) {
        this.nominationID = nominationID;
        this.rewardID = rewardID;
        this.reason = reason;
        this.userID = userID;
        this.selected = selected;
        this.hrSelected = hrSelected;
        this.evidencesList = evidencesList;
    }

    public Nominations(Long nominationID, String projectName, Long rewardID, String rewardName, String reason, Long userID, String userName, boolean selected, boolean hrSelected, Long projectId, Long managerId, List<Evidences> evidencesList) {
        this.nominationID = nominationID;
        this.projectName = projectName;
        this.rewardID = rewardID;
        this.rewardName = rewardName;
        this.reason = reason;
        this.userID = userID;
        this.userName = userName;
        this.selected = selected;
        this.hrSelected = hrSelected;
        this.projectId = projectId;
        this.managerId = managerId;
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isHrSelected() {
        return hrSelected;
    }

    public void setHrSelected(boolean hrSelected) {
        this.hrSelected = hrSelected;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRewardID(Long rewardID) {
        this.rewardID = rewardID;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}








