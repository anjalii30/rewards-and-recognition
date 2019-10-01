package com.rar.model;


import com.rar.enums.RoleEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="roles")
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_Id",unique = true,nullable = false)
    private long roleId;

    @Column(name="Role",nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            },
            mappedBy = "roles")
    private Set<UserInfo> userInfo2 = new HashSet<>();


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

    public Set<UserInfo> getUserInfo2() {
        return userInfo2;
    }

    public void setUserInfo2(Set<UserInfo> userInfo2) {
        this.userInfo2 = userInfo2;
    }


    @Override
    public String toString() {
        return "Roles{" +
                "roleId=" + roleId +
                ", role=" + role +
                '}';
    }
}
