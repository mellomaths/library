package com.mellomaths.library.infrastructure.out.mongo.book;

import com.mellomaths.library.domain.dto.BookInstanceDto;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.infrastructure.out.mongo.configuration.BookInstanceRepositoryMongoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

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
}
