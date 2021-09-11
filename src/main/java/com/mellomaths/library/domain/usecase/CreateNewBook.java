package com.mellomaths.library.domain.usecase;

import com.mellomaths.library.domain.Book;
import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.dto.NewBookDto;
import com.mellomaths.library.domain.exception.IsbnDuplicatedException;
import com.mellomaths.library.domain.repository.BookRepository;

public class CreateNewBook {

    private final BookRepository bookRepository;

    public CreateNewBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto execute(NewBookDto newBookDto) {
        Book book = newBookDto.toModel();

        BookDto existingBook = bookRepository.findByIsbn(newBookDto.getIsbn());
        if (existingBook != null) {
            throw new IsbnDuplicatedException("Book " + existingBook.getTitle() + " is already registered with ISBN " + newBookDto.getIsbn());
        }

        BookDto bookDto = BookDto.fromModel(book);
        bookRepository.save(bookDto);
        return bookDto;
    }
}
