package com.workflow.model;

import com.workflow.enums.Role;

public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private Long managerId;

    public User() {
    }

    public User(Long id,
                String name,
                String email,
                String password,
                Role role,Long managerId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setManagerId(Long managerId) { this.managerId = managerId;}

    public Long getManagerId(){ return managerId;}
}