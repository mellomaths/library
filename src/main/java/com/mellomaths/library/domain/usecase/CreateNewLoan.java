package com.mellomaths.library.domain.usecase;

import com.mellomaths.library.domain.Book;
import com.mellomaths.library.domain.Loan;
import com.mellomaths.library.domain.Patron;
import com.mellomaths.library.domain.dto.*;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.domain.repository.BookRepository;
import com.mellomaths.library.domain.repository.LoanRepository;
import com.mellomaths.library.domain.repository.PatronRepository;


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
        Book book = Book.fromDto(new GetBook(bookRepository, bookInstanceRepository).execute(bookId));
        Patron patron = Patron.fromDto(new GetPatron(patronRepository).execute(newLoanDto.getPatronId()));

        Loan loan = patron.createLoan(book, newLoanDto.getDaysToReturn());
        LoanDto loanDto = LoanDto.fromModel(loan);
        loanRepository.save(loanDto);
        bookInstanceRepository.save(BookInstanceDto.fromModel(loan.getBookInstance()));
        return loanDto;
    }

}
