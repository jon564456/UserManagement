package com.jbrigido.usermanagement.service;

import java.util.List;

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

    /**
     * Retrieves all user from database
     * @return a user list
     */
    public List<Users> getAllUsers() {
        return (List<Users>) repository.findAll();
    }

    /**
     * Retrieve a user with the same id
     * @param id of the user to retrieve from database
     * @return user with the same id if it exists
     */
    public Users getUserById(Integer id) {
        return repository.findById(id).orElse(new Users());
    }

    /**
     * Creates a new user in the database
     * @param user object to saved in the database
     */
    public void addUser(Users user) {
        String pass = user.getPassword();
        String encryptPass = BCrypt.hashpw(pass, BCrypt.gensalt());
        user.setPassword(encryptPass);
        repository.save(user);
    }

    /**
     * Deletes a user from database
     * @param id to delete from database
     */
    public void deleteUser(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("User not found!");
        }
        repository.deleteById(id);
    }

    /**
     * Updates an existing user from database
     * @param user object to update in the database
     * @return the user created
     * @throws RuntimeException with the message User not found
     */
    public Users updateUser(Users user) {
        if (!repository.existsById(user.getIduser())) {
            throw new RuntimeException("User not found!");
        }
        repository.save(user);
        return user;
    }

    /**
     * Updates partially an existing user from database
     * @param user object to update in the database
     * @return the user to updated
     * @throws RuntimeException with the message User not found
     */
    public Users patchUser(Users user) {
        if (!repository.existsById(user.getIduser())) {
            throw new RuntimeException("User not found!");
        }
        Users found = getUserById(user.getIduser());
        if (user.getUsername() != null) {
            found.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            String pass = user.getPassword();
            String encryptPass = BCrypt.hashpw(pass, BCrypt.gensalt());
            found.setPassword(encryptPass);
        }

        repository.save(found);
        return found;
    }
}
