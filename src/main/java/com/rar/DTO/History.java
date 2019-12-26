package com.rar.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "DTO for history of nomination for manager")
public class History implements Serializable {

    @ApiModelProperty(value = "The name of reward")
    private String rewardName;

    @ApiModelProperty(value = "list of nomination details by manager w.r.t. projects")
    private List<ProjectNominationHistory> projectNominationHistoryList;

    public History(String rewardName, List<ProjectNominationHistory> projectNominationHistoryList) {
        this.rewardName = rewardName;
        this.projectNominationHistoryList = projectNominationHistoryList;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public List<ProjectNominationHistory> getProjectNominationHistoryList() {
        return projectNominationHistoryList;
    }

    public void setProjectNominationHistoryList(List<ProjectNominationHistory> projectNominationHistoryList) {
        this.projectNominationHistoryList = projectNominationHistoryList;
    }
}