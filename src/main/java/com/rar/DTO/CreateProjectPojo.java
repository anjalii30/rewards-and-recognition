package com.rar.DTO;

public class CreateProjectPojo {

    private Long projectId;
    private String[] userEmail;
    private String projectName;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
