package com.rar.model;


import com.rar.enums.RoleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="roles")
@ApiModel(description = "All the details about roles")
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id",unique = true,nullable = false)
    @ApiModelProperty(notes = "The database generated Role ID")
    private long roleId;

    @Column(name="role",nullable = false)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Used to determine the type of role")
    private RoleEnum role;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            },
            mappedBy = "roles")

    private Set<UserInfo> userInfo = new HashSet<>();


    public Roles() {
    }

    public Roles(long roleId, RoleEnum role) {
        this.roleId = roleId;
        this.role = role;
    }


    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

   /* public Set<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Set<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }*/

    @Override
    public String toString() {
        return "Roles{" +
                "roleId=" + roleId +
                ", role=" + role +
                ", userInfo=" + userInfo +
                '}';
    }
}
