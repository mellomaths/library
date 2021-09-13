package com.mellomaths.library.domain.repository;

import com.mellomaths.library.domain.dto.PatronDto;

public interface PatronRepository {

    void save(PatronDto patronDto);

}
