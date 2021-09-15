package com.mellomaths.library.domain.usecase.validation;

import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.exception.IsbnDuplicatedException;
import com.mellomaths.library.domain.repository.BookRepository;

public class ValidateBookISBNDuplication {

    private final BookRepository bookRepository;

    public ValidateBookISBNDuplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void validate(String isbn) {
        BookDto existingBook = bookRepository.findByIsbn(isbn);
        if (existingBook != null) {
            throw new IsbnDuplicatedException("Book " + existingBook.getTitle() + " is already registered with ID " + existingBook.getId() + " and ISBN " + existingBook.getIsbn());
        }
    }
}