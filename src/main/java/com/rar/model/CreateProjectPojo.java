package com.rar.model;

public class CreateProjectPojo {

    private Long project_id;
    private String[] user_email;
    private String project_name;
    private String manager_email;

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

    public String getManager_email() {
        return manager_email;
    }

    public void setManager_email(String manager_email) {
        this.manager_email = manager_email;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }
}
