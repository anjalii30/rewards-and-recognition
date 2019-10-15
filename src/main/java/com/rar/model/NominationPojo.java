package com.rar.model;

import java.util.Date;
import java.util.List;

public class NominationPojo {
    private Long rewardId;
    private String project_name;
    private long userId;
    private String frequency;
    private Date start_date;
    private Date end_date;
    private String employee_name;
    private boolean selected=false;
    private boolean disable=false;
    private String reward_name;
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

    public void setProject_name(String project_name) {
        this.project_name = project_name;
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

    public String getReward_name() {
        return reward_name;
    }

    public void setReward_name(String reward_name) {
        this.reward_name = reward_name;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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
