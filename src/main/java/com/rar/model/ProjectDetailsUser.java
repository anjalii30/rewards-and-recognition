package com.rar.model;

import java.io.Serializable;

public class ProjectDetailsUser implements Serializable {

    private Long project_id;
    private String project_name;
    private Boolean working;
    private Boolean managing;

    public ProjectDetailsUser(Long project_id, String project_name, Boolean working, Boolean managing) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.working = working;
        this.managing = managing;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
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
