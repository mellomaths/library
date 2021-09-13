package com.mellomaths.library.domain.repository;

import com.mellomaths.library.domain.dto.BookInstanceDto;

import java.util.List;

public interface BookInstanceRepository {
    void save(BookInstanceDto bookInstanceDto);
    List<BookInstanceDto> findByIsbn(String isbn);
}
