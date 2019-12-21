package com.rar.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

@ApiModel(description = "DTO for history of nomination for manager")
public class History implements Serializable {

    @ApiModelProperty(value = "The name of reward")
    private String rewardName;

    @ApiModelProperty(value = "list of nomination details by manager")
    private List<UserNominationDetails> userNominationDetailsList;

    public History(String rewardName, List<UserNominationDetails> userNominationDetailsList) {
        this.rewardName = rewardName;
        this.userNominationDetailsList = userNominationDetailsList;
    }

    public History() {

    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public List<UserNominationDetails> getUserNominationDetailsList() {
        return userNominationDetailsList;
    }

    public void setUserNominationDetailsList(List<UserNominationDetails> userNominationDetailsList) {
        this.userNominationDetailsList = userNominationDetailsList;
    }
}
