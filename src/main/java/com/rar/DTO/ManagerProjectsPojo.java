package com.rar.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "The mapping of manager with projects")
public class ManagerProjectsPojo {

    @ApiModelProperty(value = "The email of the manager")
    @NotEmpty
    private String managerEmail;

    @ApiModelProperty(value = "The id of the project")
    @NotNull
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
