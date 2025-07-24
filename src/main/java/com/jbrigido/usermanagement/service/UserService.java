package com.jbrigido.usermanagement.service;

import java.util.List;

import com.jbrigido.usermanagement.dto.user.UserRequestDTO;
import com.jbrigido.usermanagement.dto.user.UserResponseDTO;
import com.jbrigido.usermanagement.exception.UserNotFound;
import com.jbrigido.usermanagement.mapper.UserMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.jbrigido.usermanagement.model.Users;
import com.jbrigido.usermanagement.repository.UserRepository;

/**
 * UserService provides methods to retrieve data from the database
 */

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper mapper;

    /**
     * Retrieves all user from database
     *
     * @return a user list
     */
    public List<Users> getAllUsers() {
        return (List<Users>) repository.findAll();
    }

    /**
     * Retrieve a user with the same id
     *
     * @param id of the user to retrieve from database
     * @return user with the same id if it exists
     */
    public Users getUserById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFound("User not found!"));
    }

    /**
     * Creates a new user in the database
     *
     * @param user object to saved in the database
     */
    public UserResponseDTO addUser(UserRequestDTO user) {
        Users mapped = mapper.mapToEntity(user);
        Users saved = repository.save(mapped);
        return mapper.mapToDTO(saved);
    }

    /**
     * Deletes a user from database
     *
     * @param id to delete from database
     */
    public void deleteUser(Integer id) {
        if (!repository.existsById(id)) {
            throw new UserNotFound("User not found");
        }
        repository.deleteById(id);
    }

    /**
     * Updates an existing user from database
     *
     * @param user object to update in the database
     * @return the user created
     * @throws RuntimeException with the message User not found
     */
    public UserResponseDTO updateUser(UserRequestDTO user) {
        if (!repository.existsById(user.getId())) {
            throw new UserNotFound("User not found");
        }
        Users mapped = mapper.mapToEntity(user);
        Users saved = repository.save(mapped);
        return mapper.mapToDTO(saved);
    }

    /**
     * Updates partially an existing user from database
     *
     * @param user object to update in the database
     * @return the user to updated
     * @throws RuntimeException with the message User not found
     */
    public UserResponseDTO patchUser(UserRequestDTO user) {
        if (!repository.existsById(user.getId())) {
            throw new UserNotFound("User not found");
        }
        Users found = getUserById(user.getId());
        if (user.getUsername() != null) {
            found.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            String pass = user.getPassword();
            found.setPassword(pass);
        }
        if (user.getEmail() != null) {
            found.setEmail(user.getEmail());
        }
        Users saved = repository.save(found);
        return mapper.mapToDTO(saved);
    }
}
