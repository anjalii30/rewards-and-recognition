package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.CrossOrigin;

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
    private long awarded_id;

    @Column(name = "reward_name")
    @ApiModelProperty(notes = "Reward name for which the person is rewarded")
    private String reward_name;

    @Column(name = "employee_id")
    @ApiModelProperty(notes = "Employee who is rewarded")
    private String emp_id;

    @Column(name = "project_name")
    @ApiModelProperty(notes = "Project for which reward is given")
    private String project_name;

    @Column(name = "subjectDescription")
    @ApiModelProperty(notes = "Description of why reward is given")
    private String subjectDescription;

    @Column(name = "employee_image")
    @ApiModelProperty(notes = "Employee image")
    private String employee_image;



    public Awarded() {
    }

    public long getAwarded_id() {
        return awarded_id;
    }

    public void setAwarded_id(long awarded_id) {
        this.awarded_id = awarded_id;
    }


    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public String getEmployee_image() {
        return employee_image;
    }

    public void setEmployee_image(String employee_image) {
        this.employee_image = employee_image;
    }

    public String getReward_name() {
        return reward_name;
    }

    public void setReward_name(String reward_name) {
        this.reward_name = reward_name;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    @Override
    public String toString() {
        return "Awarded{" +
                "awarded_id=" + awarded_id +
                ", reward_name='" + reward_name + '\'' +
                ", emp_id='" + emp_id + '\'' +
                ", project_name='" + project_name + '\'' +
                ", subjectDescription='" + subjectDescription + '\'' +
                ", employee_image='" + employee_image + '\'' +
                '}';
    }




}
