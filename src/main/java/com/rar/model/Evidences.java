package com.rar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "evidences")
public class Evidences {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "evidenceID")
    @ApiModelProperty(notes = "The database generated evidence ID")
    private Long evidenceID;

    @Column(name = "criteria_desc")
   // @ApiModelProperty(notes = "Criteria name")
    private Long criteriaName;

    @Column(name = "evidence",length = 2147483000)
  //  @ApiModelProperty(notes = "Text Evidence")
    private String evidences;

    @Column(name = "nomination_id")
    @ApiModelProperty(notes = "Nomination Id")
    private Long nominationID;

    @Column(name = "text_evidence", length = 214783000)
    @ApiModelProperty(notes = "Text Evidence")
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

    public Long getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(Long criteriaName) {
        this.criteriaName = criteriaName;
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
