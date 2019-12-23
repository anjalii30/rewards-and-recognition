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
    private String designation;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.ALL
            },
            mappedBy = "designation")
    private Set<UserInfo> userInfo = new HashSet<>();

    public Designation() {
    }

    public Designation(long designationId, String designation) {
        this.designationId = designationId;
        this.designation = designation;
    }

    public long getDid() {
        return designationId;
    }

    public void setDid(long did) {
            this.designationId = did;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Designation{" +
                "did=" + designationId +
                ", designation='" + designation + '\'' +
                '}';
    }
}
