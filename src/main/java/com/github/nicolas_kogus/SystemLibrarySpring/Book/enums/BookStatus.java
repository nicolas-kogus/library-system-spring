package com.github.nicolas_kogus.SystemLibrarySpring.Book.enums;

/**
 * Defines the possible availability states of a book.
 */
public enum BookStatus {
    /** The book is currently checked out by a user. */
    RENTED_BOOK,
    
    /** The book is available for borrowing. */
    BOOK_AVAILABLE
}
