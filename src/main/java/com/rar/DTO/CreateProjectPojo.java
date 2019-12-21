package com.rar.DTO;

import javax.validation.constraints.NotEmpty;

public class CreateProjectPojo {

    @NotEmpty
    private String[] userEmail;
    @NotEmpty
    private String projectName;
    @NotEmpty
    private String managerEmail;

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

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

}
