package com.mellomaths.library.infrastructure.out.mongo.book;

import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.repository.BookRepository;
import com.mellomaths.library.infrastructure.out.mongo.configuration.BookRepositoryMongoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
public class BookMongoRepository implements BookRepository {

    private final BookRepositoryMongoConfiguration repository;

    @Autowired
    public BookMongoRepository(final BookRepositoryMongoConfiguration repository) {
        this.repository = repository;
    }

    @Override
    public void save(BookDto bookDto) {
        BookDocument document = BookDocument.from(bookDto);
        repository.save(document);
    }

    @Override
    public BookDto findByIsbn(String isbn) {
        BookDocument document = repository.findByIsbn(isbn);
        if (document == null) {
            return null;
        }
        return document.toDto();
    }

    @Override
    public BookDto findById(String id) {
        Optional<BookDocument> document = repository.findById(id);
        if (document.isEmpty()) {
            return null;
        }

        return document.get().toDto();
    }
}
