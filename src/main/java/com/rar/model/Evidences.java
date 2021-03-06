package com.rar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;

@Entity
@Table(name = "evidences")
@ApiModel(description = "All the details about evidences submitted during nominations")
public class Evidences {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "evidenceID")
    @ApiModelProperty(notes = "The database generated evidenceID")
    private Long evidenceID;

    @Column(name = "criteria_desc", length = 2147483000)
    @ApiModelProperty(notes = "description about a particular criteria")
    private String criteria_desc;

    @Column(name = "evidence",length = 2147483000)
    @ApiModelProperty(notes = "the documented form of evidence")
    private String evidences;

    @Column(name = "nomination_id")
    @ApiModelProperty(notes = "the nomination_id attached to that particular evidences")
    private Long nominationID;

    @Column(name = "text_evidence", length = 214783000)
    @ApiModelProperty("the textual form of evidence")
    private String text_evidence;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "nomination_id", referencedColumnName = "nomination_id", insertable = false, updatable = false)
    })
    @JsonIgnore
    private Nominations nominations;

    public Long getEvidenceID() {
        return evidenceID;
    }

    public void setEvidenceID(Long evidenceID) {
        this.evidenceID = evidenceID;
    }

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

    public long getNominationID() {
        return nominationID;
    }

    public void setNominationID(long nominationID) {
        this.nominationID = nominationID;
    }

    public Nominations getNominations() {
        return nominations;
    }

    public void setNominations(Nominations nominations) {
        this.nominations = nominations;
    }

    public String getText_evidence() {
        return text_evidence;
    }

    public void setText_evidence(String text_evidence) {
        this.text_evidence = text_evidence;
    }
}
