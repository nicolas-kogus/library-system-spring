package com.github.nicolas_kogus.SystemLibrarySpring.Loan.service;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.service.BookService;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.model.Loan;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.repository.LoanRepository;
import com.github.nicolas_kogus.SystemLibrarySpring.User.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service responsible for managing the lifecycle of a Loan.
 */
@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookService bookService;

    // Using constructor injection for better testability and to follow Spring best practices
    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookService bookService) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    /**
     * Records a new loan, updates the book's availability status, and assigns the borrower.
     * 
     * @param loan The loan details containing bookId and userId.
     * @return The saved Loan entity.
     */
    @Transactional // Ensures that if one save fails, the entire operation is rolled back
    public Loan saveLoan(Loan loan) {
        // Attempt to find the book associated with the loan
        Optional<Book> opBook = bookService.locateById(loan.getBookId());

        if (opBook.isPresent()) {
            Book book = opBook.get();
            // Update book status to rented
            bookService.setBookRented(book);
            // Link the user to the book
            book.setUser(userRepository.findById(loan.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found")));
            
            // Persist the changes made to the book entity
            bookService.save(book);
        }

        // Save the loan record
        return loanRepository.save(loan);
    }
}
