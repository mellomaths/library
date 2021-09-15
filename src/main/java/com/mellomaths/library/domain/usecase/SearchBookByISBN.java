package com.mellomaths.library.domain.usecase;

import com.mellomaths.library.domain.Book;
import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.exception.IsbnNotFoundException;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.domain.repository.BookRepository;

public class SearchBookByISBN {

    private final BookRepository bookRepository;
    private final BookInstanceRepository bookInstanceRepository;

    public SearchBookByISBN(BookRepository bookRepository, BookInstanceRepository bookInstanceRepository) {
        this.bookRepository = bookRepository;
        this.bookInstanceRepository = bookInstanceRepository;
    }

    public BookDto execute(String isbn) {
        BookDto bookDto = bookRepository.findByIsbn(isbn);
        if (bookDto == null) {
            throw new IsbnNotFoundException("Book with ISBN " + isbn + " was not found. Please register the book before creating new instances.");
        }

        bookDto.setInstances(bookInstanceRepository.findByBookId(bookDto.getId()));
        return bookDto;
    }
}
