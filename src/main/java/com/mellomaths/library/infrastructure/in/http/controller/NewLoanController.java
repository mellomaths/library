package com.mellomaths.library.infrastructure.in.http.controller;

import com.mellomaths.library.domain.dto.LoanDto;
import com.mellomaths.library.domain.dto.NewLoanDto;
import com.mellomaths.library.domain.exception.*;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.domain.repository.BookRepository;
import com.mellomaths.library.domain.repository.LoanRepository;
import com.mellomaths.library.domain.repository.PatronRepository;
import com.mellomaths.library.domain.usecase.CreateNewLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class NewLoanController {

    private final BookRepository bookRepository;
    private final BookInstanceRepository bookInstanceRepository;
    private PatronRepository patronRepository;
    private LoanRepository loanRepository;

    @Autowired
    public NewLoanController(final BookRepository bookRepository, final BookInstanceRepository bookInstanceRepository, final PatronRepository patronRepository, final LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.bookInstanceRepository = bookInstanceRepository;
        this.patronRepository = patronRepository;
        this.loanRepository = loanRepository;
    }

    @PostMapping(value = "/books/{bookId}/loans")
    public LoanDto execute(@PathVariable("bookId") String bookId, @RequestBody @Valid NewLoanDto newLoanDto) {
        try {
            CreateNewLoan createNewLoan = new CreateNewLoan(bookRepository, bookInstanceRepository, patronRepository, loanRepository);
            return createNewLoan.execute(bookId, newLoanDto);
        } catch (BookNotAvailableForLoanException | PatronNotFoundException | BookNotFoundException | InvalidLoanDeadlineException | LoanDeadlineMustBeSetException ex) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), ex);
        }
    }
}
