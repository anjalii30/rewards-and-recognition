package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="projects")
@ApiModel(description = "All the details about projects")
public class Projects implements Serializable {

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


    @OneToMany(mappedBy = "projects",cascade = CascadeType.ALL)
    private List<Nominations> nominations;


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
            mappedBy = "projects")

    private Set<Manager> managers = new HashSet<>();

    public Projects() {
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

    public List<Nominations> getNominations() {
        return nominations;
    }

    public void setNominations(List<Nominations> nominations) {
        this.nominations = nominations;
    }

    public Set<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Set<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

    public Set<Manager> getManagers() {
        return managers;
    }

    public void setManagers(Set<Manager> managers) {
        this.managers = managers;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", completed=" + completed +
                ", nominations=" + nominations +
                ", userInfo=" + userInfo +
                '}';
    }
}
