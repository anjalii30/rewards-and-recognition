package com.rar.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "DTO for project and user details")
public class ProjectDetailsUser implements Serializable {

    @ApiModelProperty(value = "The id of the project")
    private Long projectId;

    @ApiModelProperty(value = "The name of the project")
    private String projectName;

    @ApiModelProperty(value = "boolean for whether the user is working or not")
    private Boolean working;

    @ApiModelProperty(value = "boolean for whether the is manager or not")
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
