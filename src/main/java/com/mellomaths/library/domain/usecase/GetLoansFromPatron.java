package com.mellomaths.library.domain.usecase;

import com.mellomaths.library.domain.dto.LoanDto;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.domain.repository.LoanRepository;
import com.mellomaths.library.domain.repository.PatronRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetLoansFromPatron {

    private final LoanRepository loanRepository;
    private final PatronRepository patronRepository;
    private final BookInstanceRepository bookInstanceRepository;

    public GetLoansFromPatron(LoanRepository loanRepository, PatronRepository patronRepository, BookInstanceRepository bookInstanceRepository) {
        this.loanRepository = loanRepository;
        this.patronRepository = patronRepository;
        this.bookInstanceRepository = bookInstanceRepository;
    }

    public List<LoanDto> execute(String patronId) {
        return loanRepository
                .findByPatronId(patronId)
                .stream().peek(loan -> {
                    loan.setPatron(patronRepository.findById(loan.getPatron().getId()));
                    loan.setBookInstance(bookInstanceRepository.findById(loan.getBookInstance().getId()));
                }).collect(Collectors.toList());
    }
}
