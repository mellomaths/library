package com.mellomaths.library.domain.usecase.validation;

import com.mellomaths.library.domain.Loan;
import com.mellomaths.library.domain.dto.LoanDto;
import com.mellomaths.library.domain.exception.LimitOfLoansPerPatronException;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.domain.repository.LoanRepository;
import com.mellomaths.library.domain.repository.PatronRepository;
import com.mellomaths.library.domain.usecase.GetLoansFromPatron;

import java.util.List;

public class ValidateLimitOfLoansPerPatron {

    private final LoanRepository loanRepository;
    private final PatronRepository patronRepository;
    private final BookInstanceRepository bookInstanceRepository;

    public ValidateLimitOfLoansPerPatron(LoanRepository loanRepository, PatronRepository patronRepository, BookInstanceRepository bookInstanceRepository) {
        this.loanRepository = loanRepository;
        this.patronRepository = patronRepository;
        this.bookInstanceRepository = bookInstanceRepository;
    }

    public void execute(String patronId) {
        List<LoanDto> loansDto = new GetLoansFromPatron(loanRepository, patronRepository, bookInstanceRepository).execute(patronId);
        int totalOfLoans = loansDto.size();
        if (totalOfLoans >= Loan.LIMIT_PER_PATRON) {
            throw new LimitOfLoansPerPatronException("Patron with ID " + patronId + " already reached 5 loans. It's needed to return a book in order to get a new one.");
        }
    }

}
