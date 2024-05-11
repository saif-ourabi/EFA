package com.example.efabackend.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "User")

public class User {
    @Id
    @Column(name = "id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email",length = 255)
    private String email;

    @Column(name = "password",length = 255)
    private String password;

    @Column(name = "name",length = 255)
    private String name;

    @Column(name = "role",length = 255)
    private String role;




    public User(long id, String email, String password, String name, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public User(String email, String password, String name, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
