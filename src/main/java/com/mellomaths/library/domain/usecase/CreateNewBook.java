package com.mellomaths.library.domain.usecase;

import com.mellomaths.library.domain.Book;
import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.dto.NewBookDto;
import com.mellomaths.library.domain.repository.BookRepository;
import com.mellomaths.library.domain.usecase.validation.ValidateBookDuplication;

public class CreateNewBook {

    private final BookRepository bookRepository;

    public CreateNewBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto execute(NewBookDto newBookDto) {
        new ValidateBookDuplication(bookRepository).validate(newBookDto.getIsbn());
        Book book = new Book(newBookDto.getTitle(), newBookDto.getPrice(), newBookDto.getIsbn());
        BookDto bookDto = BookDto.fromModel(book);
        bookRepository.save(bookDto);
        return bookDto;
    }
}
