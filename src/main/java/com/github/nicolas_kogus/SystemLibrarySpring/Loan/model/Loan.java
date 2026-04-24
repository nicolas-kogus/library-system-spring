package com.github.nicolas_kogus.SystemLibrarySpring.Loan.model;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import com.github.nicolas_kogus.SystemLibrarySpring.User.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    private Long userId;

    public Loan() {
    }

    public Loan(Long bookId, Long userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", book=" + bookId +
                ", user=" + userId +
                '}';
    }
}
