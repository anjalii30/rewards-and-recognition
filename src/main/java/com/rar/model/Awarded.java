package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import sun.util.resources.cldr.mg.LocaleNames_mg;

import javax.persistence.*;
import java.io.Serializable;

@CrossOrigin
@Entity
@Table(name = "awarded")
//@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "All details about the Awarded employees. ")
public class Awarded implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated awarded ID")
    private Long awarded_id;

    @Column(name = "reward_id")
    @ApiModelProperty(notes = "Reward name for which the person is rewarded")
    private Long reward_id;

    @Column(name = "employee_id")
    @ApiModelProperty(notes = "Employee who is rewarded")
    private Long emp_id;

    @Column(name = "project_id")
    @ApiModelProperty(notes = "Project for which reward is given")
    private Long project_id;

    @Column(name = "subjectDescription")
    @ApiModelProperty(notes = "Description of why reward is given")
    private String subjectDescription;


    public Awarded() {
    }


    public Long getAwarded_id() {
        return awarded_id;
    }

    public void setAwarded_id(Long awarded_id) {
        this.awarded_id = awarded_id;
    }

    public Long getReward_id() {
        return reward_id;
    }

    public void setReward_id(Long reward_id) {
        this.reward_id = reward_id;
    }

    public Long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Long emp_id) {
        this.emp_id = emp_id;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    @Override
    public String toString() {
        return "Awarded{" +
                "awarded_id=" + awarded_id +
                ", reward_id=" + reward_id +
                ", emp_id=" + emp_id +
                ", project_id=" + project_id +
                ", subjectDescription='" + subjectDescription + '\'' +
                '}';
    }
}
