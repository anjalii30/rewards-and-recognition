package com.rar.DTO;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "The mapping of manager with projects")
public class ManagerProjectsPojo {

    private String managerEmail;
    private Long projectId;


    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
