package com.github.nicolas_kogus.SystemLibrarySpring.Book.model;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.enums.BookStatus;
import com.github.nicolas_kogus.SystemLibrarySpring.User.model.User;
import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@Entity
@Table(name = "tb_books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private LocalDate yearOfPublication;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Book() {
    }

    public Book(String name, String author, LocalDate yearOfPublication, BookStatus bookStatus, User user) {
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.bookStatus = bookStatus;
        this.user = user;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
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
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", bookStatus=" + bookStatus +
                ", user=" + user +
                '}';
    }

}
