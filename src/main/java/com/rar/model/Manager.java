package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="managers")
@ApiModel(description = "All the details related to relation of employees")
public class Manager implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id",unique = true,nullable = false)
    @ApiModelProperty(notes = "The database generated employee_relation ID")
    private long manager_id;

    @Column(name="manager_email",nullable = false)
    @ApiModelProperty(notes = "The email ID of particular manager")
    private String manager_email;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "manager")
    private Set<UserInfo> userInfo = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name="manager_projects",
            joinColumns ={@JoinColumn(name="manager_id")},
            inverseJoinColumns = {@JoinColumn(name="project_id")}
    )
    private Set<Projects> manager_projects= new HashSet<>();

    public Manager() {
    }

    public Manager(long id, String manager_email) {
        this.manager_id = id;
        this.manager_email = manager_email;
    }

    public long getId() {
        return manager_id;
    }

    public void setId(long ERid) {
        this.manager_id = ERid;
    }

    public String getManager_email() {
        return manager_email;
    }

    public void setManager_email(String manager_email) {
        this.manager_email = manager_email;
    }

    public long getManager_id() {
        return manager_id;
    }

    public void setManager_id(long manager_id) {
        this.manager_id = manager_id;
    }

    public Set<Projects> getManager_projects() {
        return manager_projects;
    }

    public void setManager_projects(Set<Projects> manager_projects) {
        this.manager_projects = manager_projects;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + manager_id +

                ", manager_email='" + manager_email + '\'' +
                '}';
    }
}