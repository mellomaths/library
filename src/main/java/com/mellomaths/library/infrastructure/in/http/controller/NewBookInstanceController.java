package com.mellomaths.library.infrastructure.in.http.controller;

import com.mellomaths.library.domain.dto.BookInstanceDto;
import com.mellomaths.library.domain.dto.NewBookInstanceDto;
import com.mellomaths.library.domain.exception.IsbnNotFoundException;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.domain.repository.BookRepository;
import com.mellomaths.library.domain.usecase.CreateNewBookInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class NewBookInstanceController {

    private final BookRepository bookRepository;
    private final BookInstanceRepository bookInstanceRepository;

    @Autowired
    public NewBookInstanceController(final BookRepository bookRepository, final BookInstanceRepository bookInstanceRepository) {
        this.bookRepository = bookRepository;
        this.bookInstanceRepository = bookInstanceRepository;
    }

    @PostMapping(value = "/books/instances")
    public BookInstanceDto execute(@RequestBody @Valid NewBookInstanceDto newBookInstanceDto) {
        try {
            CreateNewBookInstance createNewBookInstance = new CreateNewBookInstance(bookRepository, bookInstanceRepository);
            return createNewBookInstance.execute(newBookInstanceDto);
        } catch (IsbnNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
}
