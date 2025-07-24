package com.jbrigido.usermanagement.dto.user;

/**
 * Data Transfer Object (DTO) used to represent user information
 * sent in API responses.
 */

public class UserResponseDTO {

    private Integer userid;
    private String username;
    private String email;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
