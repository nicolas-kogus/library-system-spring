package com.github.nicolas_kogus.SystemLibrarySpring.User.model;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import jakarta.persistence.*;

import java.util.List;

/**
 * Represents a User entity within the library system.
 * This class maps to the 'tb_users' table and maintains the user's 
 * credentials and their associated collection of books.
 */
@Entity
@Table(name = "tb_users")
public class User {

    /**
     * Unique identifier for the User.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * The full name of the user.
     */
    private String name;

    /**
     * The user's password. 
     * Note: In a production environment, this should be encrypted/hashed.
     */
    private String password;

    /**
     * A list of books associated with this user (e.g., currently borrowed).
     * Relationship corrected to @OneToMany as one user can have multiple books.
     */
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    /**
     * Default constructor required by JPA.
     */
    public User() {
    }

    /**
     * Constructs a new User with specific details.
     * 
     * @param name     The name of the user.
     * @param password The account password.
     * @param book    The initial list of books assigned to the user.
     */
    public User(String name, String password, Book book) {
        this.name = name;
        this.password = password;
        this.book = book;
    }

    /**
     * @return The unique ID of the user.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId The unique ID to set for the user.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set for the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password to set for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The list of books owned/borrowed by the user.
     */
    public Book getBooks() {
        return book;
    }

    /**
     * @param book The list of books to associate with the user.
     */
    public void setBooks(Book book) {
        this.book = book;
    }

    /**
     * Returns a string representation of the User.
     * Security Note: Be cautious about including the password field in logs.
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", books=" + book +
                '}';
    }
}
