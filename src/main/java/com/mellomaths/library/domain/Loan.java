package com.mellomaths.library.domain;

import com.mellomaths.library.domain.core.CustomDate;
import com.mellomaths.library.domain.core.Entity;
import com.mellomaths.library.domain.dto.LoanDto;

public class Loan extends Entity {

    public static final int LIMIT_TIME_IN_DAYS;
    public static final int LIMIT_PER_PATRON;

    static {
        LIMIT_TIME_IN_DAYS = 60;
        LIMIT_PER_PATRON = 5;
    }

    private final Integer daysToReturn;
    private final Patron patron;
    private final BookInstance bookInstance;
    private final CustomDate dueDate;

    public Loan(Patron patron, BookInstance bookInstance, Integer daysToReturn) {
        super();
        this.patron = patron;
        this.bookInstance = bookInstance;
        this.daysToReturn = daysToReturn == null || daysToReturn == 0 ? LIMIT_TIME_IN_DAYS : daysToReturn;
        this.dueDate = new CustomDate().plusDays(this.daysToReturn);
    }

    protected Loan(String id, String creationDate, Patron patron, BookInstance bookInstance, Integer daysToReturn) {
        super(id, creationDate);
        this.patron = patron;
        this.bookInstance = bookInstance;
        this.daysToReturn = daysToReturn == null || daysToReturn == 0 ? LIMIT_TIME_IN_DAYS : daysToReturn;
        this.dueDate = new CustomDate().plusDays(this.daysToReturn);
    }

    public static Loan fromDto(LoanDto loanDto) {
        return new Loan(
                loanDto.getId(),
                loanDto.getCreationDate(),
                Patron.fromDto(loanDto.getPatron()),
                BookInstance.fromDto(loanDto.getBookInstance()),
                loanDto.getDaysToReturn()
        );
    }

    public Patron getPatron() {
        return patron;
    }

    public String getPatronId() {
        return patron.getId().toString();
    }

    public BookInstance getBookInstance() {
        return bookInstance;
    }

    public String getBookId() {
        return bookInstance.getBookId();
    }

    public String getBookInstanceId() {
        return bookInstance.getId().toString();
    }

    public Integer getDaysToReturn() {
        return daysToReturn;
    }

    public CustomDate getDueDate() {
        return dueDate;
    }
}
