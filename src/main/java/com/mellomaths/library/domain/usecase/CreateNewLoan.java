package com.mellomaths.library.domain.usecase;

import com.mellomaths.library.domain.Book;
import com.mellomaths.library.domain.BookInstance;
import com.mellomaths.library.domain.Loan;
import com.mellomaths.library.domain.Patron;
import com.mellomaths.library.domain.dto.*;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.domain.repository.BookRepository;
import com.mellomaths.library.domain.repository.LoanRepository;
import com.mellomaths.library.domain.repository.PatronRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CreateNewLoan {

    private final BookRepository bookRepository;
    private final BookInstanceRepository bookInstanceRepository;
    private final PatronRepository patronRepository;
    private final LoanRepository loanRepository;

    public CreateNewLoan(BookRepository bookRepository, BookInstanceRepository bookInstanceRepository, PatronRepository patronRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.bookInstanceRepository = bookInstanceRepository;
        this.patronRepository = patronRepository;
        this.loanRepository = loanRepository;
    }

    public LoanDto execute(String bookId, NewLoanDto newLoanDto) {
        BookDto bookDto = bookRepository.findById(bookId);
        List<BookInstanceDto> bookInstancesDto = bookInstanceRepository.findByIsbn(bookDto.getIsbn());
        PatronDto patronDto = patronRepository.findById(newLoanDto.getPatronId());

        Book book = Book.fromDto(bookDto);
        List<BookInstance> instances = bookInstancesDto.stream().map(BookInstance::fromDto).collect(Collectors.toList());
        Patron patron = Patron.fromDto(patronDto);

        Loan loan = new Loan(patron, book, instances, newLoanDto.getDays());
        LoanDto loanDto = LoanDto.fromModel(loan);
        loanRepository.save(loanDto);
        return loanDto;
    }

}
