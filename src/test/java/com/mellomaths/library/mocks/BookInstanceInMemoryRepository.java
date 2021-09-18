package com.mellomaths.library.mocks;

import com.mellomaths.library.domain.BookInstance;
import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.dto.BookInstanceDto;
import com.mellomaths.library.domain.repository.BookInstanceRepository;

import java.util.*;
import java.util.stream.Collectors;

public class BookInstanceInMemoryRepository implements BookInstanceRepository {

    Map<String, BookInstanceDto> storage = new LinkedHashMap<>();

    @Override
    public void save(BookInstanceDto bookInstance) {
        storage.put(bookInstance.getId(), bookInstance);
    }

    @Override
    public BookInstanceDto findById(String id) {
        BookInstanceDto instanceFound = null;
        for (BookInstanceDto instance : storage.values()) {
            if (instance.getId().equals(id)) {
                instanceFound = instance;
            }
        }

        return instanceFound;
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

    @Override
    public List<BookInstanceDto> findByBookId(String bookId) {
        List<BookInstanceDto> booksFound = new ArrayList<>();
        for (BookInstanceDto book : storage.values()) {
            if (book.getBookId().equals(bookId)) {
                booksFound.add(book);
            }
        }

        return booksFound;
    }

}
