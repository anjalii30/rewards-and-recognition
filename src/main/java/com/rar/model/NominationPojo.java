package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "All the DTO data in nominations")
public class NominationPojo {
    @ApiModelProperty(notes = "The ID of a particular reward")
    private Long rewardId;
    @ApiModelProperty(notes = "The name of the Project")
    private String project_name;
    @ApiModelProperty(notes = "The ID of the particular user")
    private long userId;
    @ApiModelProperty(notes = "The name of the employee for that particular nomination")
    private String employee_name;
    @ApiModelProperty(notes = "Used in self nominated rewards when a manager approves it")
    private boolean selected=false;
    @ApiModelProperty(notes = "Used for rejecting multiple nominations")
    private boolean disable=false;
    @ApiModelProperty(notes = "The name of the particular reward")
    private String reward_name;
    @ApiModelProperty(notes = "The reason for nomination")
    private String reason;
    @ApiModelProperty(notes = "The list of evidences for a particular nomination")
    private List<EvidencesPojo> evidencesPojoList;

    public List<EvidencesPojo> getEvidencesPojoList() {
        return evidencesPojoList;
    }

    public void setEvidencesPojoList(List<EvidencesPojo> evidencesPojoList) {
        this.evidencesPojoList = evidencesPojoList;
    }

    public long getRewardId() {
        return rewardId;
    }

    public void setRewardId(long rewardId) {
        this.rewardId = rewardId;
    }

    public String getProject_name() {
        return project_name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getReward_name() {
        return reward_name;
    }

    public void setReward_name(String reward_name) {
        this.reward_name = reward_name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
