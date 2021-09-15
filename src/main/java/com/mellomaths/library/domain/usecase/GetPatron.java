package com.mellomaths.library.domain.usecase;

import com.mellomaths.library.domain.dto.PatronDto;
import com.mellomaths.library.domain.exception.PatronNotFoundException;
import com.mellomaths.library.domain.repository.PatronRepository;

public class GetPatron {

    private final PatronRepository patronRepository;

    public GetPatron(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public PatronDto execute(String id) {
        PatronDto patronDto = patronRepository.findById(id);
        if (patronDto == null) {
            throw new PatronNotFoundException("Patron with ID " + id + " was not found.");
        }

        return patronDto;
    }

}
