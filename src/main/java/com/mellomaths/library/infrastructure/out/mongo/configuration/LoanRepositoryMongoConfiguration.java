package com.mellomaths.library.infrastructure.out.mongo.configuration;

import com.mellomaths.library.infrastructure.out.mongo.loan.LoanDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanRepositoryMongoConfiguration extends MongoRepository<LoanDocument, String> {
}
