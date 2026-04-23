package com.github.nicolas_kogus.SystemLibrarySpring.Book.model;

import com.github.nicolas_kogus.SystemLibrarySpring.User.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tb_book")
public class Book {

    private Long id;
    private String name;
    private String author;
    private LocalDate yearOfPublication;

    private User user;

    public Book() {
    }

    public Book(String name, String author, LocalDate yearOfPublication, User user) {
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.user = user;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(LocalDate yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", user=" + user +
                '}';
    }
}
