package com.rar.model;

import java.util.Date;
import java.util.List;

public class NominationPojo {
    long rewardId;
    String projectName;
    long userId;
    String frequency;
    Date startDate;
    Date endingDate;
    List<EvidencesPojo> evidencesPojoList;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }
}
