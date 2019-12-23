package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@ApiModel(description = "All the details about User")
public class UserInfo implements Serializable {
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",unique = true,nullable = false)
    @ApiModelProperty(notes = "The database generated User ID")
    private Long userId;

    @NotEmpty
    @Email
    @Size(max = 100)
    @Column(unique = true)
    @ApiModelProperty(notes = "The email ID of the User")
    private String email;

    @Column(name = "wallet")
    private double wallet=0;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The name of the User")
    @NotEmpty
    private String name;

    @Column
    @ApiModelProperty(notes = "User to store the image of User")
    private Boolean firstSign=false;

    @Column
    @ApiModelProperty(notes = "The URL for user's Image")
    private String imageUrl;


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

    public UserInfo(Long userId, @NotNull @Email @Size(max = 100) String email, String name, Boolean firstSign, String imageUrl,Set<Designation> designation, Set<Roles> roles, Set<Projects> projects) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.firstSign = firstSign;
        this.imageUrl = imageUrl;
        this.designation = designation;
        this.roles = roles;
        this.projects = projects;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long uid) {
        this.userId = uid;
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

    public String getWallet() {
        return df2.format(wallet);
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + userId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", firstSign=" + firstSign +
                ", imageUrl='" + imageUrl + '\'' +
                ", designation=" + designation +
                ", roles=" + roles +
                ", projects=" + projects +
                '}';
    }
}
