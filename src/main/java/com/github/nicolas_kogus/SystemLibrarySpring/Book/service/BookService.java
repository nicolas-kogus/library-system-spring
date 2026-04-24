package com.github.nicolas_kogus.SystemLibrarySpring.Book.service;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.enums.BookStatus;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service class for managing book-related business operations.
 */
@Service
public class BookService {

    private final BookRepository repository;

    /**
     * Constructor-based dependency injection for BookRepository.
     *
     * @param bookRepository the repository to interact with the database.
     */
    public BookService(BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    /**
     * Registers a new book in the system after validating uniqueness.
     *
     * @param book the book entity to be registered.
     * @return a ResponseEntity containing the saved book object on success, 
     *         or an error message if the book already exists.
     */
    public ResponseEntity<?> registerBook(Book book) {
        // Check if a book with the same name already exists in the database
        if (repository.existsByName(book.getName())) {
            return ResponseEntity.badRequest().body("Error: Book already registered!");
        }

        // Initialize book status as available before persisting
        book.setBookStatus(BookStatus.BOOK_AVAILABLE);

        // Save the book entity and return the result
        return ResponseEntity.ok(repository.save(book));
    }
}
