package com.github.nicolas_kogus.SystemLibrarySpring.User.service;

import com.github.nicolas_kogus.SystemLibrarySpring.User.model.User;
import com.github.nicolas_kogus.SystemLibrarySpring.User.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> registerUser(@RequestBody User user) {
            if (userRepository.existsByName(user.getName())) {

                return ResponseEntity.badRequest().body("Error: user already registered!");
            }

            return ResponseEntity.ok(userRepository.save(user));
    }

    public List<User> listAllUsers() {return userRepository.findAll();}
}
