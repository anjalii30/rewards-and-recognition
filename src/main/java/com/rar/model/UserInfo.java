package com.rar.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="users")

public class UserInfo implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",unique = true,nullable = false)
    private Long uid;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column
    private Boolean firstSign=false;

    @Column
    private String imageUrl;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })

    @JoinTable(
            name = "user_employeeRelation",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "EmployeeRelation_Id")}
    )

    private Set<EmployeeRelation> employeeRelation = new HashSet<>();



    //DESIGNATION

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "user_designation",
            joinColumns = {@JoinColumn(name = "User_Id")},
            inverseJoinColumns = {@JoinColumn(name = "Designation_Id")}
    )
    private Set<Designation> designation = new HashSet<>();




    //ROLES

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Roles> roles = new HashSet<>();



    //Projects

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name="user_projects",
            joinColumns ={@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="project_id")}
    )
    private Set<Projects> projects= new HashSet<>();


    public UserInfo() {

    }

    public UserInfo(Long uid, @NotNull @Email @Size(max = 100) String email, String name, Boolean firstSign, String imageUrl, Set<EmployeeRelation> employeeRelation, Set<Designation> designation, Set<Roles> roles, Set<Projects> projects) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.firstSign = firstSign;
        this.imageUrl = imageUrl;
        this.employeeRelation = employeeRelation;
        this.designation = designation;
        this.roles = roles;
        this.projects = projects;
    }



    //
//    public UserInfo(Long uid, @NotNull @Email @Size(max = 100) String email, String name, RoleEnum role, DesignationEnum designation, Boolean firstSign, String imageUrl, Set<EmployeeRelation> employeeRelation) {
//        this.uid = uid;
//        this.email = email;
//        this.name = name;
//        this.role = role;
//        this.designation = designation;
//        this.firstSign = firstSign;
//        this.imageUrl = imageUrl;
//        this.employeeRelation = employeeRelation;
//    }



    public Long getId() {
        return uid;
    }

    public void setId(Long uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public Boolean getFirstSign() {
        return firstSign;
    }

    public void setFirstSign(Boolean firstSign) {
        this.firstSign = firstSign;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<EmployeeRelation> getEmployeeRelation() {
        return employeeRelation;
    }

    public void setEmployeeRelation(Set<EmployeeRelation> employeeRelation) {
        this.employeeRelation = employeeRelation;
    }


    public Set<Designation> getDesignation() {
        return designation;
    }

    public void setDesignation(Set<Designation> designation) {
        this.designation = designation;
    }


    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", firstSign=" + firstSign +
                ", imageUrl='" + imageUrl + '\'' +
                ", employeeRelation=" + employeeRelation +
                ", designation=" + designation +
                ", roles=" + roles +
                ", projects=" + projects +
                '}';
    }
}
