package com.mellomaths.library.infrastructure.out.mongo.configuration;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = BookRepositoryConfiguration.class)
public class MongoConfiguration {

}