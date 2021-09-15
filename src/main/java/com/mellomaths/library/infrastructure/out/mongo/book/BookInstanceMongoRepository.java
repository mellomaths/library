package com.mellomaths.library.infrastructure.out.mongo.book;

import com.mellomaths.library.domain.dto.BookInstanceDto;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.infrastructure.out.mongo.configuration.BookInstanceRepositoryMongoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class BookInstanceMongoRepository implements BookInstanceRepository {

    private final BookInstanceRepositoryMongoConfiguration repository;

    @Autowired
    public BookInstanceMongoRepository(final BookInstanceRepositoryMongoConfiguration repository) {
        this.repository = repository;
    }

    @Override
    public void save(BookInstanceDto bookInstanceDto) {
        BookInstanceDocument document = BookInstanceDocument.from(bookInstanceDto);
        repository.save(document);
    }

    @Override
    public List<BookInstanceDto> findByIsbn(String isbn) {
        List<BookInstanceDocument> documents = repository.findByIsbn(isbn);
        return documents.stream().map(BookInstanceDocument::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BookInstanceDto> findByBookId(String bookId) {
        List<BookInstanceDocument> documents = repository.findByBookId(bookId);
        return documents.stream().map(BookInstanceDocument::toDto).collect(Collectors.toList());
    }
}
