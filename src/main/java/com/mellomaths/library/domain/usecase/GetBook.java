package com.mellomaths.library.domain.usecase;

import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.exception.BookNotFoundException;
import com.mellomaths.library.domain.exception.IsbnNotFoundException;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.domain.repository.BookRepository;

public class GetBook {
    private final BookRepository bookRepository;
    private final BookInstanceRepository bookInstanceRepository;

    public GetBook(BookRepository bookRepository, BookInstanceRepository bookInstanceRepository) {
        this.bookRepository = bookRepository;
        this.bookInstanceRepository = bookInstanceRepository;
    }

    public BookDto execute(String id) {
        BookDto bookDto = bookRepository.findById(id);
        if (bookDto == null) {
            throw new BookNotFoundException("Book with ID " + id + " was not found.");
        }

        bookDto.setInstances(bookInstanceRepository.findByBookId(bookDto.getId()));
        return bookDto;
    }
}
