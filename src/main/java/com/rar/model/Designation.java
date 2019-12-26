package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="designation")
@ApiModel(description = "All the details about types of designations")
public class Designation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "designation_id",unique = true,nullable = false)
    @ApiModelProperty(notes = "The database generated designation ID")
    private long designationId;

    @Column(name="designation",nullable = false)
    @NotEmpty
    @ApiModelProperty(notes = "Designation name")
    private String designationName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.ALL
            },
            mappedBy = "designation")
    private Set<UserInfo> userInfo = new HashSet<>();

    public Designation() {
        // empty constructor
    }


    public long getDesignationId() {
        return designationId;
    }

    public void setDesignationId(long did) {
            this.designationId = did;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public Set<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Set<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }
}
