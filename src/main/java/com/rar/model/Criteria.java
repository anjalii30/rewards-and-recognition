package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="criteria")

@ApiModel(description = "All details about the criterion. ")
public class Criteria implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "criteria_id")
    @ApiModelProperty(notes = "The database generated criteria ID")
    private long criteriaId;

    @Column(name="criteria_desc",nullable = false)
    @NotEmpty
    @ApiModelProperty(notes = "The description of specific criteria")
    private String criteriaDesc;

    @OneToMany(
            mappedBy = "criteria",cascade = CascadeType.REFRESH)
    private List<RewardsCriteria> rewards = new ArrayList<>();

    public Criteria() {
    }

    public Criteria(long criteriaId, @NotEmpty String criteriaDesc) {
        this.criteriaId = criteriaId;
        this.criteriaDesc = criteriaDesc;
    }

    public Criteria(String criteriaDesc) {
        this.criteriaDesc = criteriaDesc;
    }

    public long getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(long criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getCriteriaDesc() {
        return criteriaDesc;
    }

    public void setCriteriaDesc(String criteriaDesc) {
        this.criteriaDesc = criteriaDesc;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "criteriaId=" + criteriaId +
                ", criteriaDesc='" + criteriaDesc +
                '}';
    }
}
