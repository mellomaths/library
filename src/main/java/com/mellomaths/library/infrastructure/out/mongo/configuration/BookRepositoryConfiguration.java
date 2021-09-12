package com.mellomaths.library.infrastructure.out.mongo.configuration;

import com.mellomaths.library.infrastructure.out.mongo.book.BookDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepositoryConfiguration extends MongoRepository<BookDocument, String> {
    BookDocument findByIsbn(String isbn);
}
