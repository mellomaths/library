package com.mellomaths.library.infrastructure.out.mongo.loan;

import com.mellomaths.library.domain.dto.LoanDto;
import com.mellomaths.library.domain.repository.LoanRepository;
import com.mellomaths.library.infrastructure.out.mongo.configuration.LoanRepositoryMongoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class LoanMongoRepository implements LoanRepository {

    private final LoanRepositoryMongoConfiguration repository;

    @Autowired
    public LoanMongoRepository(final LoanRepositoryMongoConfiguration repository) {
        this.repository = repository;
    }

    @Override
    public void save(LoanDto loanDto) {
        LoanDocument document = LoanDocument.from(loanDto);
        repository.save(document);
    }
}
