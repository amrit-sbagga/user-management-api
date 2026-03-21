package com.amrit.user_management_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateUserRequest {

    @NotBlank(message = "Username cannot be empty")
    @Schema(example = "monty1")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Schema(example = "password123")
    private String password;

    @Min(18)
    @Max(60)
    @Min(value = 18, message = "Age must be greater than 18")
    @Schema(example = "25")
    private Integer age;

    @NotNull(message = "Role cannot be null")
    @Schema(example = "USER")
    private Role role;

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
