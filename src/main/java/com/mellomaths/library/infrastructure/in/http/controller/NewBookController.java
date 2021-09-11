package com.mellomaths.library.infrastructure.in.http.controller;

import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.dto.NewBookDto;
import com.mellomaths.library.domain.exception.IsbnDuplicatedException;
import com.mellomaths.library.domain.repository.BookRepository;
import com.mellomaths.library.domain.usecase.CreateNewBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class NewBookController {

    private final BookRepository bookRepository;

    @Autowired
    public NewBookController(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping(value = "/books")
    public BookDto execute(@RequestBody @Valid NewBookDto newBookDto) {
        try {
            CreateNewBook createNewBook = new CreateNewBook(this.bookRepository);
            return createNewBook.execute(newBookDto);
        } catch (IsbnDuplicatedException ex) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), ex);
        }
    }
}
