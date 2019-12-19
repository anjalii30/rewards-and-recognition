package com.rar.DTO;

import java.io.Serializable;

public class ProjectDetailsUser implements Serializable {

    private Long projectId;
    private String projectName;
    private Boolean working;
    private Boolean managing;

    public ProjectDetailsUser(Long projectId, String projectName, Boolean working, Boolean managing) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.working = working;
        this.managing = managing;
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

    public Boolean getWorking() {
        return working;
    }

    public void setWorking(Boolean working) {
        this.working = working;
    }

    public Boolean getManaging() {
        return managing;
    }

    public void setManaging(Boolean managing) {
        this.managing = managing;
    }
}
