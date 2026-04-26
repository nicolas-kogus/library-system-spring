package com.github.nicolas_kogus.SystemLibrarySpring.User.repository;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import com.github.nicolas_kogus.SystemLibrarySpring.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for {@link User} entities.
 * Extends {@link JpaRepository} to provide standard CRUD operations
 * and includes custom query methods for user-specific data access.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their name.
     *
     * Note: It's generally recommended to return an {@link Optional<User>}
     * for methods that might not find a result, to explicitly handle the absence of a user.
     *
     * @param name The name of the user to find.
     * @return The name of the user if found, otherwise null.
     */
    // Consider changing the return type to Optional<User> for better null safety.
    User findByName(String name); // Changed from String to User to match the entity

    /**
     * Checks if a user with the given name already exists in the database.
     *
     * @param name The name to check for existence.
     * @return {@code true} if a user with the given name exists, {@code false} otherwise.
     */
    Boolean existsByName(String name);
    Boolean existsByBook(Book book);
}
