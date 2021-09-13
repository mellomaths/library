package com.mellomaths.library.domain.repository;

import com.mellomaths.library.domain.dto.BookDto;

public interface BookRepository {
    void save(BookDto book);
    BookDto findByIsbn(String isbn);
    BookDto findById(String id);
}
