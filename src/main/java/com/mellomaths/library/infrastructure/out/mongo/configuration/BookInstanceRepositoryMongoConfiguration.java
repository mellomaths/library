package com.mellomaths.library.infrastructure.out.mongo.configuration;

import com.mellomaths.library.infrastructure.out.mongo.book.BookInstanceDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookInstanceRepositoryMongoConfiguration extends MongoRepository<BookInstanceDocument, String> {
    List<BookInstanceDocument> findByIsbn(String isbn);
    List<BookInstanceDocument> findByBookId(String bookId);
}
