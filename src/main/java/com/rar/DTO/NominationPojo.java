package com.rar.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(description = "All the DTO data in nominations")
public class NominationPojo {

    @ApiModelProperty(notes = "The ID of a particular reward")
    @NotNull
    private Long rewardId;

    @ApiModelProperty(notes = "The name of the reward")
    private String  rewardName;

    @ApiModelProperty(notes = "The name of the project")
    private String projectName;

    @ApiModelProperty(notes = "The ID of the particular user")
    @NotNull
    private long userId;

    @ApiModelProperty(notes = "The name of the employee")
    private String userName;

    @ApiModelProperty(notes = "Used in self nominated rewards when a manager approves it")
    private boolean selected=false;

    @ApiModelProperty(notes = "Used for selecting awardee by HR")
    private boolean hrSelected=false;

    @ApiModelProperty(notes = "The reason for nomination")
    private String reason;

    @ApiModelProperty(notes = "The ID of a particular project")
    @NotNull
    private Long projectId;

    @ApiModelProperty(notes = "The ID of a the manager")
    @NotNull
    private Long managerId;

    @ApiModelProperty(notes="manager name")
    private String managerName;
    @ApiModelProperty(notes = "The list of evidences for a particular nomination")
    private List<EvidencesPojo> evidencesPojoList;

    public List<EvidencesPojo> getEvidencesPojoList() {
        return evidencesPojoList;
    }

    public void setEvidencesPojoList(List<EvidencesPojo> evidencesPojoList) {
        this.evidencesPojoList = evidencesPojoList;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isHrSelected() {
        return hrSelected;
    }

    public void setHrSelected(boolean hrSelected) {
        this.hrSelected = hrSelected;
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
