package com.example.demo.entity;

import com.example.demo.enums.Permission;
import com.example.demo.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "username is required")
    @Column(unique = true)
    private String username;

    @NotEmpty(message = "password is required")
    private String password;

    //@ManyToMany(fetch = FetchType.EAGER)
    //private Set<Role> roles = new HashSet<>();

    @NotEmpty(message = "role is required")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    protected User() {}

    public User(String userName, String password, Set<Role> roles) {
        this.username = userName;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
