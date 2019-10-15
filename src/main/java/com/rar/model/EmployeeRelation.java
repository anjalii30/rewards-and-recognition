package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="employeeRelation")
@ApiModel(description = "All the details related to relation of employees")
public class EmployeeRelation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeRelation_Id",unique = true,nullable = false)
    @ApiModelProperty(notes = "The database generated employee_relation ID")
    private long ERid;



    @Column(name="manager_email",nullable = false)
    @ApiModelProperty(notes = "The email ID of particular manager")
    private String manager_email;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "employeeRelation")
    private Set<UserInfo> userInfo = new HashSet<>();


    public EmployeeRelation() {
    }

    public EmployeeRelation(long id, String manager_email) {
        this.ERid = id;
        this.manager_email = manager_email;
    }


    public long getId() {
        return ERid;
    }

    public void setId(long ERid) {
        this.ERid = ERid;
    }

    public String getManager_email() {
        return manager_email;
    }

    public void setManager_email(String manager_email) {
        this.manager_email = manager_email;
    }
/*

    public Set<UserInfo> getUserInfo() {
        return userInfo;
    }
*/

    public long getERid() {
        return ERid;
    }

    public void setERid(long ERid) {
        this.ERid = ERid;
    }
/*
    public void setUserInfo(Set<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }*/

    @Override
    public String toString() {
        return "EmployeeRelation{" +
                "id=" + ERid +

                ", manager_email='" + manager_email + '\'' +
                '}';
    }
}