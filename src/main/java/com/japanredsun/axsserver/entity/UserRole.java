package com.japanredsun.axsserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"Username","User_Role"}))
public class UserRole {
    private int roleId;
    private User user;
    private String userRole;



    @Id
    @Column(name = "Role_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Username")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "User_Role", length = 30,nullable = false)
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
