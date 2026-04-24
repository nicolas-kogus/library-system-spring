package com.github.nicolas_kogus.SystemLibrarySpring.User.controller;

import com.github.nicolas_kogus.SystemLibrarySpring.User.model.User;
import com.github.nicolas_kogus.SystemLibrarySpring.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller providing endpoints for user account management.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * Registers a new user in the system.
     * 
     * @param user the user details to persist.
     * @return ResponseEntity with the result of the registration.
     */
    @PostMapping("/save-user")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return service.registerUser(user);
    }

    /**
     * Updates an existing user's information.
     * 
     * @param user the updated user data.
     * @param id the unique identifier of the user.
     * @return the updated user entity.
     */
    @PostMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(name = "id") Long id) {
        user.setUserId(id);
        return service.updateUser(user);
    }

    /**
     * Retrieves a list of all registered users.
     * 
     * @return list of User entities.
     */
    @GetMapping("/list-allUsers")
    public List<User> findAllUsers() {
        return service.listAllUsers();
    }

}
