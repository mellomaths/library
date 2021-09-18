package com.mellomaths.library.infrastructure.out.mongo.configuration;

import com.mellomaths.library.infrastructure.out.mongo.loan.LoanDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LoanRepositoryMongoConfiguration extends MongoRepository<LoanDocument, String> {
    List<LoanDocument> findByPatronId(String patronId);
}
