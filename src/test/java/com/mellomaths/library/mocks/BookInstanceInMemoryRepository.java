package com.mellomaths.library.mocks;

import com.mellomaths.library.domain.BookInstance;
import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.dto.BookInstanceDto;
import com.mellomaths.library.domain.repository.BookInstanceRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BookInstanceInMemoryRepository implements BookInstanceRepository {

    Map<String, BookInstanceDto> storage = new LinkedHashMap<>();

    @Override
    public void save(BookInstanceDto bookInstance) {
        storage.put(bookInstance.getId(), bookInstance);
    }

    @Override
    public List<BookInstanceDto> findByIsbn(String isbn) {
        List<BookInstanceDto> booksFound = new ArrayList<>();
        for (BookInstanceDto book : storage.values()) {
            if (book.getIsbn().equals(isbn)) {
                booksFound.add(book);
            }
        }

        return booksFound;
    }

}
