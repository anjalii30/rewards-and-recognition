package com.rar.DTO;

import java.io.Serializable;
import java.util.List;

public class History implements Serializable {

    private String rewardName;

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
