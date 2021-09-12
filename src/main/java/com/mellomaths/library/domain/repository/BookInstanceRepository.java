package com.mellomaths.library.domain.repository;

import com.mellomaths.library.domain.dto.BookInstanceDto;

public interface BookInstanceRepository {
    void save(BookInstanceDto bookInstanceDto);
}
