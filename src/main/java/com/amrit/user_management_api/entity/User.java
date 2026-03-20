package com.amrit.user_management_api.entity;

import com.amrit.user_management_api.dto.request.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name cannot be empty")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Min(value = 18, message = "Age must be greater than 18")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role cannot be empty")
    private Role role;

    // REQUIRED by JPA
    public User() {
    }

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public User(String username, String password, Integer age, Role role) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
