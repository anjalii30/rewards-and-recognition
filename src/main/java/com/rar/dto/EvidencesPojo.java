package com.rar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "DTO for evidences")
public class EvidencesPojo {

    @ApiModelProperty(notes = "description about a particular criteria")
    private String  criteriaDesc;

    @ApiModelProperty(notes = "the documented form of evidence")
    private String evidences;

    @ApiModelProperty(notes = "the textual form of evidence")
    private String textEvidence;

    public String getCriteriaDesc() {
        return criteriaDesc;
    }

    public void setCriteriaDesc(String criteriaDesc) {
        this.criteriaDesc = criteriaDesc;
    }

    public String getEvidences() {
        return evidences;
    }

    public void setEvidences(String evidences) {
        this.evidences = evidences;
    }

    public String getTextEvidence() {
        return textEvidence;
    }

    public void setTextEvidence(String textEvidence) {
        this.textEvidence = textEvidence;
    }
}
