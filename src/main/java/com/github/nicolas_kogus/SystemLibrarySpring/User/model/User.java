package com.github.nicolas_kogus.SystemLibrarySpring.User.model;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String password;

    @OneToMany
    @JoinColumn(name = "bookId")
    private List<Book> books;

    public User() {
    }

    public User(String name, String password, List<Book> books) {
        this.name = name;
        this.password = password;
        this.books = books;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", books=" + books +
                '}';
    }
}
