package com.rar.model;

import java.io.Serializable;

public class ProjectDetailsUsers implements Serializable {

    private Long project_id;
    private String project_name;
    private Boolean managing;
    private Boolean working;

    public ProjectDetailsUsers(Long project_id, String project_name, Boolean managing, Boolean working) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.managing = managing;
        this.working = working;
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

    public Boolean getManaging() {
        return managing;
    }

    public void setManaging(Boolean managing) {
        this.managing = managing;
    }

    public Boolean getWorking() {
        return working;
    }

    public void setWorking(Boolean working) {
        this.working = working;
    }
}
