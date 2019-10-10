package com.rar.model;


import com.rar.enums.DesignationEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="designation")
public class Designation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Designation_Id",unique = true,nullable = false)
    private long designationId;

    @Column(name="Designation",nullable = false)
    @Enumerated(EnumType.STRING)
    private DesignationEnum designation;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            },
            mappedBy = "designation")
    private Set<UserInfo> userInfo = new HashSet<>();

    public Designation() {
    }

    public Designation(long designationId, DesignationEnum designation) {
        this.designationId = designationId;
        this.designation = designation;
    }

    public long getDid() {
        return designationId;
    }

    public void setDid(long did) {
            this.designationId = did;
    }


    public DesignationEnum getDesignation() {
        return designation;
    }

    public void setDesignation(DesignationEnum designation) {
        this.designation = designation;
    }

/*    public Set<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Set<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }*/



    @Override
    public String toString() {
        return "Designation{" +
                "did=" + designationId +
                ", designation='" + designation + '\'' +
                '}';
    }
}
