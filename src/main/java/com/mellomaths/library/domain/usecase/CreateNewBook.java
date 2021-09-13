package com.mellomaths.library.domain.usecase;

import com.mellomaths.library.domain.Book;
import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.dto.NewBookDto;
import com.mellomaths.library.domain.repository.BookRepository;
import com.mellomaths.library.domain.usecase.validation.ValidateBookDuplication;

public class CreateNewBook {

    private final BookRepository bookRepository;
    private final ValidateBookDuplication validateBookDuplication;

    public CreateNewBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.validateBookDuplication = new ValidateBookDuplication(bookRepository);
    }

    public BookDto execute(NewBookDto newBookDto) {
        Book book = newBookDto.toModel();
        validateBookDuplication.validate(newBookDto.getIsbn());
        BookDto bookDto = BookDto.fromModel(book);
        bookRepository.save(bookDto);
        return bookDto;
    }
}
