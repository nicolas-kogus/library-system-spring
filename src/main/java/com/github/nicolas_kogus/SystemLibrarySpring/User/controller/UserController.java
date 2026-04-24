package com.github.nicolas_kogus.SystemLibrarySpring.User.controller;

import com.github.nicolas_kogus.SystemLibrarySpring.User.model.User;
import com.github.nicolas_kogus.SystemLibrarySpring.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/save-user")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return service.registerUser(user);
    }

    @PostMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(name = "id") Long id) {
        user.setUserId(id);
        return service.updateUser(user);
    }

    @GetMapping("/list-allUsers")
    public List<User> findAllUsers() {
        return service.listAllUsers();
    }

}
