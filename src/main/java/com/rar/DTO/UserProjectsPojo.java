package com.rar.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "The mapping of users with projects")
public class UserProjectsPojo  {

    @NotEmpty
    @ApiModelProperty(notes = "The email of the User")
    private String[] userEmail;

    @NotNull
    @ApiModelProperty(notes = "The ID of the Project")
    private Long projectId;

    public String[] getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String[] userEmail) {
        this.userEmail = userEmail;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}