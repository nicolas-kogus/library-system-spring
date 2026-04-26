package com.github.nicolas_kogus.SystemLibrarySpring.User.service;

import com.github.nicolas_kogus.SystemLibrarySpring.User.model.User;
import com.github.nicolas_kogus.SystemLibrarySpring.User.repository.UserRepository;
import jakarta.persistence.GeneratedValue;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

/**
 * Service class responsible for handling business logic related to Users.
 */
@Service
public class UserService {

    private final UserRepository repository;

    /**
     * Constructor-based dependency injection for the UserRepository.
     * @param userRepository The repository to interact with the database.
     */
    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    /**
     * Registers a new user in the system if the name is not already taken.
     * Note: It is generally recommended to return a DTO or the saved Object
     * and handle ResponseEntity in the Controller layer.
     *
     * @param user The user entity to be registered.
     * @return ResponseEntity containing the saved user or an error message.
     */
    public ResponseEntity<?> registerUser(User user) {
            // Check if a user with the same name already exists
            if (repository.existsByName(user.getName())) {
                return ResponseEntity.badRequest().body("Error: user already registered!");
            }

            // Persist the new user to the database
            return ResponseEntity.ok(repository.save(user));
    }

    /**
     * Retrieves a list of all users registered in the system.
     * @return A list of User objects.
    **/

    public List<User> listAllUsers() {return repository.findAll();}

    public User updateUser(@RequestBody User user) {
        return repository.save(user);
    }

    public void deleteUserById(Long id) {repository.deleteById(id);}
}
