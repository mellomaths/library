package com.mellomaths.library.domain;

import com.mellomaths.library.domain.core.Entity;
import com.mellomaths.library.domain.dto.PatronDto;
import com.mellomaths.library.domain.exception.BookNotAvailableForLoanException;
import com.mellomaths.library.domain.exception.InvalidLoanDeadlineException;

import java.util.Optional;

public class Patron extends Entity {

    private final PatronType type;

    public Patron(String type) {
        super();
        this.type = PatronType.valueOf(type);
    }

    private Patron(String id, String creationDate, String type) {
        super(id, creationDate);
        this.type = PatronType.valueOf(type);
    }

    public static Patron fromDto(PatronDto patronDto) {
        return new Patron(patronDto.getId(), patronDto.getCreationDate(), patronDto.getType());
    }

    public boolean isRegular() {
        return type.equals(PatronType.REGULAR);
    }

    public boolean isResearcher() {
        return type.equals(PatronType.RESEARCHER);
    }

    public Loan createLoan(Book book, int days) {
        if (!isLoanDeadlineValid(days)) {
            throw new InvalidLoanDeadlineException("Invalid Loan deadline time.");
        }

        if (!book.canBeLoanedTo(this)) {
            throw new BookNotAvailableForLoanException("Book is not available for loans.");
        }

        Optional<BookInstance> availableInstance = book.getAvailableInstance(this);
        if (availableInstance.isEmpty()) {
            throw new BookNotAvailableForLoanException("Book is not available for loans.");
        }

        BookInstance instance = availableInstance.get();
        return instance.createLoan(this, days);
    }

    public boolean isLoanDeadlineValid(int days) {
        return type.acceptLoanTime(days);
    }

    public PatronType getType() {
        return type;
    }
}
