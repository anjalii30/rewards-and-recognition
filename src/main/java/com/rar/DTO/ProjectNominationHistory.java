package com.rar.DTO;

import java.io.Serializable;
import java.util.List;

public class ProjectNominationHistory implements Serializable {

    private Long projectId;
    private String projectName;
    private List<UserNominationDetails> userNominationDetailsList;

    public ProjectNominationHistory() {
    }

    public ProjectNominationHistory(Long projectId, String projectName, List<UserNominationDetails> userNominationDetailsList) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.userNominationDetailsList = userNominationDetailsList;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<UserNominationDetails> getUserNominationDetailsList() {
        return userNominationDetailsList;
    }

    public void setUserNominationDetailsList(List<UserNominationDetails> userNominationDetailsList) {
        this.userNominationDetailsList = userNominationDetailsList;
    }
}
