package com.rar.pojo;

import com.rar.enums.DesignationEnum;

import java.io.Serializable;

public class DesignationSelected implements Serializable {
    private long did;
    private DesignationEnum Designation;
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

    public void setDesigSelected(Boolean designationSelected) {
        this.designationSelected = designationSelected;
    }
}
