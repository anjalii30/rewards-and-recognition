package com.rar.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="criteria")

@ApiModel(description = "All details about the criterion. ")
public class Criteria implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "criteria_Id")
    @ApiModelProperty(notes = "The database generated criteria ID")
    private long criteriaId;

    @Column(name="criteria_desc",nullable = false)
    @ApiModelProperty(notes = "The description of specific criteria")
    private String criteria_desc;

    @OneToMany(
            mappedBy = "criteria",cascade = CascadeType.REFRESH)
    private List<RewardsCriteria> rewards = new ArrayList<>();

    public Criteria() {
    }

    public Criteria(String criteria_desc) {
        this.criteria_desc = criteria_desc;
    }

    public long getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(long criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getCriteria_desc() {
        return criteria_desc;
    }

    public void setCriteria_desc(String criteria_desc) {
        this.criteria_desc = criteria_desc;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "criteriaId=" + criteriaId +
                ", criteria_desc='" + criteria_desc + '\'' +
                '}';
    }
}
