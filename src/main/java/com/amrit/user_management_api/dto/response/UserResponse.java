package com.amrit.user_management_api.dto.response;

import com.amrit.user_management_api.dto.request.Role;

public class UserResponse {

    private int id;
    private String username;
    private Integer age;
    private Role role;

    public UserResponse() {}

    public UserResponse(int id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
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
