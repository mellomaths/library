package com.mellomaths.library.domain;

import com.mellomaths.library.domain.core.Entity;
import com.mellomaths.library.domain.exception.*;

import java.util.List;
import java.util.stream.Collectors;

public class Loan extends Entity {

    public static final int LIMIT_DEADLINE_IN_DAYS;

    static {
        LIMIT_DEADLINE_IN_DAYS = 60;
    }

    private String patronId;
    private String bookId;
    private String bookInstanceId;
    private int days;

    private final Patron patron;
    private final Book book;
    private final List<BookInstance> possibleInstances;
    private BookInstance bookInstance;

    public Loan(Patron patron, Book book, List<BookInstance> instances, int days) {
        super();
        this.patron = patron;
        this.book = book;
        this.possibleInstances = instances;
        this.days = days;
        validate();
        setLimitDeadlineToResearcher();
        chooseBookInstance();

        setPatronId(patron);
        setBookId(book);
    }

    public void validate() {
        if (book == null) {
            throw new BookNotFoundException("Book not found");
        }

        if (patron == null) {
            throw new PatronNotFoundException("Patron not found");
        }

        validateDeadline();
        validateNonResearcherPatronDeadline();
    }

    private void validateDeadline() {
        int limitDeadlineInDays = Loan.LIMIT_DEADLINE_IN_DAYS;
        if (days > limitDeadlineInDays) {
            throw new InvalidLoanDeadlineException("Loan deadline must be less than " + limitDeadlineInDays);
        }
    }

    private void validateNonResearcherPatronDeadline() {
        if (!patron.isResearcher() && days == 0) {
            throw new LoanDeadlineMustBeSetException("Only Researchers can request a loan without deadline.");
        }
    }

    private void setLimitDeadlineToResearcher() {
        if (patron.isResearcher() && days == 0) {
            days = Loan.LIMIT_DEADLINE_IN_DAYS;
        }
    }

    private void chooseBookInstance() {
        if (possibleInstances.size() == 0) {
            throw new BookNotAvailableForLoanException("Book Instances not found. This book is not available for Loan in the moment.");
        }

        List<BookInstance> restrictedInstances = possibleInstances
                .stream().filter(BookInstance::isRestricted).collect(Collectors.toList());
        List<BookInstance> circulatingInstances = possibleInstances
                .stream().filter(BookInstance::isCirculating).collect(Collectors.toList());

        if (circulatingInstances.size() == 0 && patron.isRegular()) {
            throw new BookNotAvailableForLoanException("No Circulating instances found. This book is not available for Regular Patrons in the moment.");
        }

        if (restrictedInstances.size() > 0 && patron.isResearcher()) {
            bookInstance = restrictedInstances.get(0);
        } else {
            bookInstance = circulatingInstances.get(0);
        }

        setBookInstanceId(bookInstance);
    }

    public String getPatronId() {
        return patronId;
    }

    private void setPatronId(Patron patron) {
        patronId = patron.getId().toString();
    }

    public String getBookId() {
        return bookId;
    }

    private void setBookId(Book book) {
        bookId = book.getId().toString();
    }

    public int getDays() {
        return days;
    }

    public String getBookInstanceId() {
        return bookInstanceId;
    }

    private void setBookInstanceId(BookInstance bookInstance) {
        bookInstanceId = bookInstance.getId().toString();
    }
}
