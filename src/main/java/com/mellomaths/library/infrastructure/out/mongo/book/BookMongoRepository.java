package com.mellomaths.library.infrastructure.out.mongo.book;

import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.repository.BookRepository;
import com.mellomaths.library.infrastructure.out.mongo.configuration.BookRepositoryConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BookMongoRepository implements BookRepository {

    private final BookRepositoryConfiguration repository;

    @Autowired
    public BookMongoRepository(final BookRepositoryConfiguration repository) {
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
}
