package com.github.nicolas_kogus.SystemLibrarySpring.Book.repository;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Book} entity.
 * Provides standard CRUD operations via JpaRepository.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Checks if a book exists in the database by its name.
     * 
     * @param name the title of the book to check.
     * @return true if a book with the given name exists, false otherwise.
     */
    Boolean existsByName(String name);
}
