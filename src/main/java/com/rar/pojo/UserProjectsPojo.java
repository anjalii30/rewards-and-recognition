package com.rar.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The mapping of users with projects")
public class UserProjectsPojo  {

    @ApiModelProperty(notes = "The email of the User")
    private String[] user_email;

    @ApiModelProperty(notes = "The name of the Project")
    private String project_name;

    public String[] getUser_email() {
        return user_email;
    }

    public void setUser_email(String[] user_email) {
        this.user_email = user_email;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
}