package com.mellomaths.library.infrastructure.out.mongo.configuration;

import com.mellomaths.library.infrastructure.out.mongo.book.BookInstanceDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookInstanceRepositoryMongoConfiguration extends MongoRepository<BookInstanceDocument, String> {
}
