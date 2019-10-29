package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All the evidences stored in another table for nominations")
public class EvidencesPojo {
    @ApiModelProperty(notes = "description about a particular criteria")
    private String  criteria_desc;
    @ApiModelProperty(notes = "the documented form of evidence")
    private String evidences;
    @ApiModelProperty(notes = "the textual form of evidence")
    private String text_evidence;

    public String getCriteria_desc() {
        return criteria_desc;
    }

    public void setCriteria_desc(String criteria_desc) {
        this.criteria_desc = criteria_desc;
    }

    public String getEvidences() {
        return evidences;
    }

    public void setEvidences(String evidences) {
        this.evidences = evidences;
    }

    public String getText_evidence() {
        return text_evidence;
    }

    public void setText_evidence(String text_evidence) {
        this.text_evidence = text_evidence;
    }
}
