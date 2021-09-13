package com.mellomaths.library.infrastructure.out.mongo.patron;

import com.mellomaths.library.domain.dto.PatronDto;
import com.mellomaths.library.domain.repository.PatronRepository;
import com.mellomaths.library.infrastructure.out.mongo.configuration.PatronRepositoryMongoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PatronMongoRepository implements PatronRepository {

    private final PatronRepositoryMongoConfiguration repository;

    @Autowired
    public PatronMongoRepository(final PatronRepositoryMongoConfiguration repository) {
        this.repository = repository;
    }

    @Override
    public void save(PatronDto patronDto) {
        PatronDocument document = PatronDocument.from(patronDto);
        repository.save(document);
    }
}
