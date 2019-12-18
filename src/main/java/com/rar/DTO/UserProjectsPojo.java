package com.rar.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The mapping of users with projects")
public class UserProjectsPojo  {

    @ApiModelProperty(notes = "The email of the User")
    private String[] user_email;

    @ApiModelProperty(notes = "The name of the Project")
    private Long project_id;

    public String[] getUser_email() {
        return user_email;
    }

    public void setUser_email(String[] user_email) {
        this.user_email = user_email;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }
}