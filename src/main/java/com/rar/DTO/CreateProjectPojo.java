package com.rar.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "DTO for creating new project")
public class CreateProjectPojo {

    @ApiModelProperty(value = "list of employees")
    @NotEmpty
    private String[] userEmail;

    @ApiModelProperty(value = "The name of the project")
    @NotEmpty
    private String projectName;

    @ApiModelProperty(value = "The email of the manager")
    @NotEmpty
    private String managerEmail;

    public String[] getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String[] userEmail) {
        this.userEmail = userEmail;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

}
