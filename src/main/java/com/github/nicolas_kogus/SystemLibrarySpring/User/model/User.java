package com.github.nicolas_kogus.SystemLibrarySpring.User.model;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;

    private List<Book> books;

    public User() {
    }

    public User(String name, String password, List<Book> books) {
        this.name = name;
        this.password = password;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", books=" + books +
                '}';
    }
}
