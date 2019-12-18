package com.rar.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The mapping of users with projects")
public class UserProjectsPojo  {

    @ApiModelProperty(notes = "The email of the User")
    private String[] userEmail;

    @ApiModelProperty(notes = "The name of the Project")
    private String projectName;

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
}