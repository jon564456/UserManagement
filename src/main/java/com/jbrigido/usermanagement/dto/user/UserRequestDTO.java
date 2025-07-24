package com.jbrigido.usermanagement.dto.user;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) used to represent user information
 * received in API requests, such as for user creation or updates.
 */
public class UserRequestDTO {

    private Integer id;
    @NotBlank(message = "The username cannot be blank")
    private String username;
    @NotBlank(message = "The password cannot be blank")
    private String password;
    @NotBlank(message = "The email cannot be blank")
    private String email;

    public UserRequestDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
