package com.rar.pojo;

import java.io.Serializable;
import java.util.List;

public class EditUserDetails implements Serializable {

    private long uid;
    private String email;
    private String name;
    private List<DesignationSelected> designationSelected;
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
