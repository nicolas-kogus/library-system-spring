package com.github.nicolas_kogus.SystemLibrarySpring.Book.controller;


import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.repository.BookRepository;
import com.github.nicolas_kogus.SystemLibrarySpring.Book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller providing endpoints for book management.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Endpoint to create a new book entry.
     * 
     * @param book the book data transfer object.
     * @return ResponseEntity with the created book or error message.
     */
    @PostMapping("save-book")
    public ResponseEntity<?> saveBook(@RequestBody Book book) {return bookService.registerBook(book);}
    
}
