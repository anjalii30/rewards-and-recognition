package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel(description = "All the DTO data in nominations")
public class NominationPojo {
    @ApiModelProperty(notes = "The ID of a particular reward")
    private Long rewardId;
    @ApiModelProperty(notes = "The name of the reward")
    private String  reward_name;
    @ApiModelProperty(notes = "The name of the project")
    private String project_name;
    @ApiModelProperty(notes = "The ID of the particular user")
    private long userId;
    @ApiModelProperty(notes = "The name of the employee")
    private String user_name;
    @ApiModelProperty(notes = "Used in self nominated rewards when a manager approves it")
    private boolean selected=false;
    @ApiModelProperty(notes = "Used for selecting awardee by HR")
    private boolean hr_selected=false;
    @ApiModelProperty(notes = "The reason for nomination")
    private String reason;
    @ApiModelProperty(notes = "The ID of a particular project")
    private Long project_id;
    @ApiModelProperty(notes = "The ID of a the manager")
    private Long managerId;
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

    public String getReward_name() {
        return reward_name;
    }

    public void setReward_name(String reward_name) {
        this.reward_name = reward_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
