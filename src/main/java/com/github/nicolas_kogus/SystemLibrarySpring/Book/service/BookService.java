package com.github.nicolas_kogus.SystemLibrarySpring.Book.service;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.enums.BookStatus;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


//      Service class responsible for handling business logic related to Books.
@Service
public class BookService {

    private final BookRepository bookRepository;

//      Constructor-based dependency injection for the BookRepository.
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

//      Logic to register a new book in the system.
//      It validates uniqueness by name before persisting the entity.

    public ResponseEntity<?> registerBook(Book book) {
        // Validate if a book with the same name already exists in the database
        if (bookRepository.existsByName(book.getName())) {
            return ResponseEntity.badRequest().body("Error: Book already registered!");
        }

        // Defaulting the book status to available upon registration
        book.setBookStatus(BookStatus.BOOK_AVAILABLE);

        // Save the book entity and return it within an OK response
        return ResponseEntity.ok(bookRepository.save(book));
    }
}
