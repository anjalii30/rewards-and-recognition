package com.rar.DTO;

import com.rar.enums.DesignationEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(description = "DTO for designations")
public class DesignationSelected implements Serializable {
    @ApiModelProperty(value = "The id of designation")
    private long did;

    @ApiModelProperty(value = "The name of the designation")
    private DesignationEnum Designation;

    @ApiModelProperty(value = "The boolean for whether the designation id selected or not")
    private Boolean designationSelected;

    public DesignationSelected(long did, DesignationEnum designation, Boolean designationSelected) {
        this.did = did;
        Designation = designation;
        this.designationSelected = designationSelected;
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public DesignationEnum getDesignation() {
        return Designation;
    }

    public void setDesignation(DesignationEnum designation) {
        Designation = designation;
    }

    public Boolean getDesignationSelected() {
        return designationSelected;
    }

    public void setDesignationSelected(Boolean designationSelected) {
        this.designationSelected = designationSelected;
    }
}
