package com.github.nicolas_kogus.SystemLibrarySpring.Loan.service;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.repository.BookRepository;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.service.BookService;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.exception.GlobalExceptionHandler;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.exception.ResourceNotFound;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.model.Loan;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.repository.LoanRepository;
import com.github.nicolas_kogus.SystemLibrarySpring.User.model.User;
import com.github.nicolas_kogus.SystemLibrarySpring.User.repository.UserRepository;
import com.github.nicolas_kogus.SystemLibrarySpring.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    /** Service containing business logic for User management. */
    private final UserService userService;

    /** Global handler for capturing and processing exceptions across the application. */
    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    /**
     * Constructs a new LoanService with required dependencies.
     * Uses constructor injection for better testability and to follow Spring best practices.
     * @param loanRepository the repository for loans
     * @param userRepository the repository for users
     * @param bookService the service for book-related operations
     * @param bookRepository the repository for books
     * @param userService the service for user-related operations
     */
    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookService bookService, BookRepository bookRepository, UserService userService) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.userService = userService;
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
                    .orElseThrow(() -> new ResourceNotFound("User not found.")));
            // Note: .get() is used here assuming the user exists based on the previous check.

            // 4. Update the relationship on the user side by linking the book to the user's collection
            // This maintains the bidirectional consistency between User and Book entities.
            optionalBook.get().getUser().setBooks(bookRepository.findById(loan.getBookId()).orElseThrow());

            // 5. Persist the changes made to the book entity (status and user association)
            bookService.save(book);
            
        } else {
                // Logical branch: If the book does not exist, we cannot proceed with the loan.
                // Fail fast if the book is not found in the database
                throw new ResourceNotFound("Book not found.");
        }

        // 6. Finally, persist the loan record itself
        return loanRepository.save(loan);
    }

    /**
     * Retrieves a loan record by its unique identifier.
     *
     * @param id The ID of the loan to retrieve.
     * @return The Loan entity if found, or null if it does not exist.
     * @throws NullPointerException if the loan does not exist.
     */
    public Loan findLoanById(Long id) {return loanRepository.findById(id).orElse(null);}

    /**
     * Deletes a loan record and resets the associated book and user states.
     * This operation ensures the book becomes available again and the user's reference is cleared.
     *
     * @param loanId The ID of the loan to be deleted.
     */
    @Transactional
    public void deleteLoanById(Long loanId) {

        // First check: Ensure the loan exists before attempting to clean up associations.
        if(findLoanById(loanId) == null) {
            throw new ResourceNotFound("Loan not found.");
        }

        // Fetch the loan to get reference to the user and book IDs
        Optional<Loan> loan = Optional.ofNullable(findLoanById(loanId));

        if (loan.isPresent()) {

            // Retrieve the specific user and book entities involved in the loan
            // These are needed to reset their states back to "available/unassigned".
            User user = userRepository.findById(loan.get().getUserId()).orElseThrow();
            Book book = bookRepository.findById(loan.get().getBookId()).orElseThrow();

            // Dissociate the book from the user entity
            user.setBooks(null);

            // Dissociate the user from the book entity
            book.setUser(null);
            // Update the book's status to reflect availability
            bookService.setBookAvailable(book);

        } else {

            // Redundant safety check: Throw exception if the loan is missing during the process.
            throw new ResourceNotFound("Loan not found.");

        }

        // Remove the loan record from the database
        // The @Transactional annotation ensures that the record is only removed if all previous steps succeed.
        loanRepository.deleteById(loanId);

    }
}
