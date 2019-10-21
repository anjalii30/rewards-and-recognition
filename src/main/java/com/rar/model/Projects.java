package com.rar.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="projects")
@ApiModel(description = "All the details about projects")
public class Projects {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated project ID")
    private Long project_id;

    @Column(name = "project_name",unique = true,nullable = false)
    @ApiModelProperty(notes = "The name of the Project")
    private String project_name;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            },
            mappedBy = "projects")

    private Set<UserInfo> userInfo = new HashSet<>();


    public Projects() {
    }

    public Projects(Long project_id, String project_name, Set<UserInfo> userInfo) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.userInfo = userInfo;
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

   /* public Set<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Set<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }
*/
    @Override
    public String toString() {
        return "Projects{" +
                "project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
