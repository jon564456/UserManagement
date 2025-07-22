package com.jbrigido.usermanagement.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jbrigido.usermanagement.model.Users;
import com.jbrigido.usermanagement.service.UserService;

/**
 * REST controller that provides endpoints to manage user data.
 * Supports operations such as retrieval, creation, update, and deletion of users.
 */

@RestController
@RequestMapping("/v1")
public class UserController {
    /**
     * Injection dependencies
     */
    @Autowired
    private UserService service;

    /**
     * Retrieves the list of all users.
     *
     * @return HTTP 200 OK with the list of users
     */

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = service.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return HTTP 200 OK with the user data if found
     */

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        Users found = service.getUserById(id);
        return ResponseEntity.ok().body(found);
    }

    /**
     * Creates a new user and returns the URI of the created resource.
     *
     * @param user the user to create
     * @return HTTP 201 Created with location header
     */

    @PostMapping
    public ResponseEntity<Users> addUser(@RequestBody Users user) {

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(user.getIduser())
                .toUri();
        service.addUser(user);
        return ResponseEntity.created(location).build();
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     * @return HTTP 204 No Content if deleted successfully
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing user.
     *
     * @param user the user object with updated data
     * @return HTTP 204 No Content if updated successfully
     */

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Users user) {
        service.updateUser(user);
        return ResponseEntity.noContent().build();
    }

    /**
     * Partially updates an existing user.
     *
     * @param user the user object with partial data
     * @return HTTP 204 No Content if updated successfully
     */

    @PatchMapping
    public ResponseEntity<?> updatePatchUser(@RequestBody Users user) {
        service.patchUser(user);
        return ResponseEntity.noContent().build();
    }

}
