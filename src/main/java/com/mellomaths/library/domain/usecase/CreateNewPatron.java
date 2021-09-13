package com.mellomaths.library.domain.usecase;

import com.mellomaths.library.domain.Patron;
import com.mellomaths.library.domain.dto.NewPatronDto;
import com.mellomaths.library.domain.dto.PatronDto;
import com.mellomaths.library.domain.repository.PatronRepository;

public class CreateNewPatron {

    private final PatronRepository repository;

    public CreateNewPatron(PatronRepository repository) {
        this.repository = repository;
    }

    public PatronDto execute(NewPatronDto newPatronDto) {
        Patron patron = newPatronDto.toModel();
        PatronDto patronDto = PatronDto.fromModel(patron);
        repository.save(patronDto);
        return patronDto;
    }
}
