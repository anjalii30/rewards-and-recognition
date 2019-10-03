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

    @Column(name = "criteria_name")
    private Long criteriaName;

    @Column(name = "evidence",length = 2147483000)
    private String evidences;

    @Column(name = "nomination_id")
    private Long nominationID;

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

}
