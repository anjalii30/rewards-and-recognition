package com.rar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "DTO for designations")
public class DesignationSelected implements Serializable {
    @ApiModelProperty(value = "The id of designation")
    private long did;

    @ApiModelProperty(value = "The name of the designation")
    private String designation;

    @ApiModelProperty(value = "The boolean for whether the designation id selected or not")
    private Boolean designationSelect;

    public DesignationSelected(long did, String designation, Boolean designationSelect) {
        this.did = did;
        this.designation = designation;
        this.designationSelect = designationSelect;

    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Boolean getDesignationSelect() {
        return designationSelect;
    }

    public void setDesignationSelect(Boolean designationSelect) {
        this.designationSelect = designationSelect;
    }
}


