package com.mellomaths.library.infrastructure.in.http.controller;

import com.mellomaths.library.domain.dto.NewPatronDto;
import com.mellomaths.library.domain.dto.PatronDto;
import com.mellomaths.library.domain.repository.PatronRepository;
import com.mellomaths.library.domain.usecase.CreateNewPatron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class NewPatronController {

    private final PatronRepository patronRepository;

    @Autowired
    public NewPatronController(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @PostMapping(value = "/patrons")
    public PatronDto execute(@RequestBody @Valid NewPatronDto newPatronDto) {
        CreateNewPatron createNewPatron = new CreateNewPatron(patronRepository);
        return createNewPatron.execute(newPatronDto);
    }
}
