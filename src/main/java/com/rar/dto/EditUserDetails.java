package com.rar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "DTO for editing a user")
public class EditUserDetails implements Serializable {

    @ApiModelProperty(value = "The id of user")
    private long uid;

    @ApiModelProperty(value = "Email of user")
    private String email;

    @ApiModelProperty(value = "name of the user")
    private String name;

    @ApiModelProperty(value = "list of designations")
    private List<DesignationSelected> designationSelected;

    @ApiModelProperty(value = "list of projects")
    private List<ProjectDetailsUser> projectsList;

    public EditUserDetails(long uid, String email, String name, List<DesignationSelected> designationSelected, List<ProjectDetailsUser> projectsList) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.designationSelected = designationSelected;
        this.projectsList = projectsList;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DesignationSelected> getDesignationSelected() {
        return designationSelected;
    }

    public void setDesignationSelected(List<DesignationSelected> designationSelected) {
        this.designationSelected = designationSelected;
    }

    public List<ProjectDetailsUser> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<ProjectDetailsUser> projectsList) {
        this.projectsList = projectsList;
    }
}
