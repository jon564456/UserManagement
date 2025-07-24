package com.jbrigido.usermanagement.mapper;

import com.jbrigido.usermanagement.dto.user.UserRequestDTO;
import com.jbrigido.usermanagement.dto.user.UserResponseDTO;
import com.jbrigido.usermanagement.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * Provide methods to convert user entity to UserResponseDTO or UserRequestDTO
 */
@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * Converts a Users entity into a UserResponseDTO
     *
     * @param user the user entity into a ResponseDTO
     * @return the corresponding UserResponseDTO
     */
    public UserResponseDTO mapToDTO(Users user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserid(user.getIduser());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        return dto;
    }

    /**
     * @param dto object that represents a request
     * @return user object
     */
    public Users mapToEntity(UserRequestDTO dto) {
        Users user = new Users();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        return user;
    }

}
