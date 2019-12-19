package com.rar.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The mapping of users with projects")
public class UserProjectsPojo  {

    @ApiModelProperty(notes = "The email of the User")
    private String[] userEmail;

    @ApiModelProperty(notes = "The ID of the Project")
    private Long projectId;

    public String[] getUserEmail() {
        return userEmail;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setUserEmail(String[] userEmail) {
        this.userEmail = userEmail;
    }

//    public Long getProjectId() {
//        return project_id;
//    }
//
//    public void setProjectId(Long projectId) {
//        this.project_id = projectId;
   // }
}