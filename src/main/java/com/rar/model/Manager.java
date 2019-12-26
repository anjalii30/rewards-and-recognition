package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="managers")
@ApiModel(description = "All the details related to relation of employees")
public class Manager implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id",unique = true,nullable = false)
    @ApiModelProperty(notes = "The database generated manager ID")
    private long managerId;

    @Column(name="manager_email",nullable = false)
    @Email
    @NotEmpty
    @ApiModelProperty(notes = "The email ID of particular manager")
    private String managerEmail;

    @OneToMany(mappedBy = "manager",cascade = CascadeType.ALL)
    private List<Nominations> nominations;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            })
    @JoinTable(
            name="manager_projects",
            joinColumns ={@JoinColumn(name="manager_id")},
            inverseJoinColumns = {@JoinColumn(name="project_id")}
    )
    private Set<Projects> manager_projects= new HashSet<>();

    public Manager() {
    }

    public Manager(long id, String managerEmail) {
        this.managerId = id;
        this.managerEmail = managerEmail;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long ERid) {
        this.managerId = ERid;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "manager_id=" + managerId +
                ", manager_email='" + managerEmail + '\'' +
                ", manager_projects=" + manager_projects +
                '}';
    }
}