package com.github.nicolas_kogus.SystemLibrarySpring.Loan.service;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.repository.BookRepository;
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
     * This operation is transactional to ensure data consistency across multiple entities.
     *
     * @param loan The loan details containing bookId and userId.
     * @return The saved Loan entity.
     * @throws RuntimeException if the user already has an active book loan.
     * @throws NullPointerException if the book or user is not found in the database.
     */
    @Transactional // Ensures that if any part of the process fails, the entire operation is rolled back
    public Loan saveLoan(Loan loan) {
        // 1. Attempt to find the book associated with the loan request
        Optional<Book> optionalBook = bookService.locateById(loan.getBookId());

            if (optionalBook.isPresent()) {

                // Check user eligibility: Business rule states a user can only have one active loan at a time.
                // We fetch the user and verify if their 'books' reference is already populated.
                if (userRepository.findById(loan.getUserId()).get().getBooks() != null) {

                    // If the user already has a book, prevent the loan creation.
                    throw new RuntimeException("This user already has a borrowed book.");

                }

            // Extract the book from the optional container now that we know it exists.
            Book book = optionalBook.get();

            // 2. Update book availability status to rented
            bookService.setBookRented(book);

            // 3. Associate the user with the book. Throws exception if user ID is invalid.
            book.setUser(userRepository.findById(loan.getUserId())
                    .orElseThrow(() -> new NullPointerException("User not found.")));

            // 4. Update the relationship on the user side by linking the book to the user's collection
            optionalBook.get().getUser().setBooks(bookRepository.findById(loan.getBookId()).orElseThrow());

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
