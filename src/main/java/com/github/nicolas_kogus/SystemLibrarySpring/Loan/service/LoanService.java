package com.github.nicolas_kogus.SystemLibrarySpring.Loan.service;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.service.BookService;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.model.Loan;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.repository.LoanRepository;
import com.github.nicolas_kogus.SystemLibrarySpring.User.model.User;
import com.github.nicolas_kogus.SystemLibrarySpring.User.repository.UserRepository;
import com.github.nicolas_kogus.SystemLibrarySpring.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    public Loan saveLoan(Loan loan) {
        Optional<Book> opBook = bookService.locateById(loan.getBookId());

        if (opBook.isPresent()) {
            bookService.setBookRented(opBook.get());
            opBook.get().setUser(userRepository.findById(loan.getUserId()).orElseThrow(NullPointerException::new));
        }

        return loanRepository.save(loan);
    }
}
