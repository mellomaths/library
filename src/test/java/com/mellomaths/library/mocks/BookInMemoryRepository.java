package com.mellomaths.library.mocks;

import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.repository.BookRepository;

import java.util.LinkedHashMap;
import java.util.Map;

public class BookInMemoryRepository implements BookRepository {

    Map<String, BookDto> storage = new LinkedHashMap<>();

    @Override
    public void save(BookDto book) {
        storage.put(book.getId(), book);
    }

    @Override
    public BookDto findByIsbn(String isbn) {
        BookDto bookFound = null;
        for (BookDto book : storage.values()) {
            if (book.getIsbn().equals(isbn)) {
                bookFound = book;
            }
        }

        return bookFound;
    }

    @Override
    public BookDto findById(String id) {
        return storage.get(id);
    }
}
