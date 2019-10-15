package com.rar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "evidences")
public class Evidences {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "evidenceID")
    private Long evidenceID;

    @Column(name = "criteria_desc", length = 2147483000)
    private String criteria_desc;

    @Column(name = "evidence",length = 2147483000)
    private String evidences;

    @Column(name = "nomination_id")
    private Long nominationID;

    @Column(name = "text_evidence", length = 214783000)
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
