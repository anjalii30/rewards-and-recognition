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

    /*private Boolean working; private Boolean managing;*/

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

 /*
    public Projects(Long project_id, String project_name, Boolean working, Boolean managing) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.working = working;
        this.managing = managing;
    }
*/

/*    public void setManaging(Boolean managing) {
        this.managing = managing;
    }

    public Boolean getWorking() {
        return working;
    }

    public Boolean getManaging() {
        return managing;
    }*/

    @Override
    public String toString() {
        return "Projects{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                '}';
    }
/*    public void setWorking(Boolean working) {
        this.working = working;
    }*/
}
