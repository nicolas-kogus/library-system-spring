package com.github.nicolas_kogus.SystemLibrarySpring.Loan.service;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.repository.BookRepository;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.service.BookService;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.model.Loan;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.repository.LoanRepository;
import com.github.nicolas_kogus.SystemLibrarySpring.User.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service responsible for managing the lifecycle of a Loan.
 */
@Service
public class LoanService {

    /** Repository for performing CRUD operations on Loan entities. */
    private final LoanRepository loanRepository;

    /** Repository for performing CRUD operations on User entities. */
    private final UserRepository userRepository;

    /** Repository for performing CRUD operations on Book entities. */
    private final BookRepository bookRepository;

    /** Service containing business logic for Book management. */
    private final BookService bookService;

    /**
     * Constructs a new LoanService with required dependencies.
     * Uses constructor injection for better testability and to follow Spring best practices.
     * @param loanRepository the repository for loans
     * @param userRepository the repository for users
     * @param bookService the service for book-related operations
     * @param bookRepository the repository for books
     */
    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookService bookService, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    /**
     * Records a new loan, updates the book's availability status, and assigns the borrower.
     * 
     * @param loan The loan details containing bookId and userId.
     * @return The saved Loan entity.
     */
    @Transactional // Ensures that if one save fails, the entire operation is rolled back
    public Loan saveLoan(Loan loan) {
        // 1. Attempt to find the book associated with the loan request
        Optional<Book> opBook = bookService.locateById(loan.getBookId());

        if (opBook.isPresent()) {
            Book book = opBook.get();

            // 2. Update book availability status to rented
            bookService.setBookRented(book);

            // 3. Associate the user with the book. Throws exception if user ID is invalid.
            book.setUser(userRepository.findById(loan.getUserId())
                    .orElseThrow(() -> new NullPointerException("User not found.")));

            // 4. Update the relationship on the user side by linking the book to the user's collection
            opBook.get().getUser().setBooks(bookRepository.findById(loan.getBookId()).orElseThrow());

            // 5. Persist the changes made to the book entity (status and user association)
            bookService.save(book);
            
        } else {
            // Fail fast if the book is not found in the database
            throw new NullPointerException("Book not found");
        }

        // 6. Finally, persist the loan record itself
        return loanRepository.save(loan);
    }
}
