package com.mellomaths.library.infrastructure.out.mongo.configuration;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = { BookRepositoryMongoConfiguration.class, BookInstanceRepositoryMongoConfiguration.class, PatronRepositoryMongoConfiguration.class, LoanRepositoryMongoConfiguration.class })
public class MongoConfiguration {

}