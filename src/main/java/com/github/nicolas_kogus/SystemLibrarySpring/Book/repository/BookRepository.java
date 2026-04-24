package com.github.nicolas_kogus.SystemLibrarySpring.Book.repository;

import com.github.nicolas_kogus.SystemLibrarySpring.Book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
