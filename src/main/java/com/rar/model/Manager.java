package com.rar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="employeeRelation")
public class Manager implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeRelation_Id",unique = true,nullable = false)
    private long ERid;



    @Column(name="manager_email",nullable = false)
    private String manager_email;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "manager")
    private Set<UserInfo> userInfo = new HashSet<>();


    public Manager() {
    }

    public Manager(long id, String manager_email) {
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
        return "Manager{" +
                "id=" + ERid +

                ", manager_email='" + manager_email + '\'' +
                '}';
    }
}