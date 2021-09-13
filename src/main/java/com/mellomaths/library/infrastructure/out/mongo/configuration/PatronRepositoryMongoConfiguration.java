package com.mellomaths.library.infrastructure.out.mongo.configuration;

import com.mellomaths.library.infrastructure.out.mongo.patron.PatronDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatronRepositoryMongoConfiguration extends MongoRepository<PatronDocument, String> {
}
