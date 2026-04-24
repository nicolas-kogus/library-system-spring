package com.github.nicolas_kogus.SystemLibrarySpring.User.controller;

import com.github.nicolas_kogus.SystemLibrarySpring.User.model.User;
import com.github.nicolas_kogus.SystemLibrarySpring.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save-user")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/list-allUsers")
    public List<User> findAllUsers() {
        return userService.listAllUsers();
    }
}
