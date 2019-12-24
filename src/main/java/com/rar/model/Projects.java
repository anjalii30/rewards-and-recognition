package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="projects")
@ApiModel(description = "All the details about projects")
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated project ID")
    @Column(name="project_id")
    private Long projectId;

    @Column(name = "project_name",unique = true,nullable = false)
    @ApiModelProperty(notes = "The name of the Project")
    @NotEmpty
    private String projectName;

    @Column(name = "completed")
    @ApiModelProperty(notes = "The boolean which used for status of project")
    private boolean completed;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            },
            mappedBy = "projects")

    private Set<UserInfo> userInfo = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            },
            mappedBy = "manager_projects")

    private Set<Manager> managers = new HashSet<>();

    public Projects() {
    }

    public Projects(Long projectId, String projectName, Set<UserInfo> userInfo) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.userInfo = userInfo;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", completed=" + completed +
                ", userInfo=" + userInfo +
                ", managers=" + managers +
                '}';
    }
}
