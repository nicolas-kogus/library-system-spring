package com.github.nicolas_kogus.SystemLibrarySpring.Loan.service;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.service.BookService;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.model.Loan;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository repository;

    @Autowired
    private BookService bookService;

    public Loan saveLoan(Loan loan) {
        Optional<Book> bookOptional = bookService.locateById(loan.getBookId());

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            bookService.setBookRented(book);
            bookService.save(book);
        }

        return repository.save(loan);
    }
}
