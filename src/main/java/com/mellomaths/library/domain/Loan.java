package com.mellomaths.library.domain;

import com.mellomaths.library.domain.core.CustomDate;
import com.mellomaths.library.domain.core.Entity;

public class Loan extends Entity {

    public static final int LIMIT_TIME_IN_DAYS;

    static {
        LIMIT_TIME_IN_DAYS = 60;
    }

    private final int days;
    private final Patron patron;
    private final BookInstance bookInstance;
    private final CustomDate dueDate;

    public Loan(Patron patron, BookInstance bookInstance, int days) {
        super();
        this.patron = patron;
        this.bookInstance = bookInstance;
        this.days = days;
        this.dueDate = new CustomDate().plusDays(days);
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

    public int getDays() {
        return days;
    }

    public CustomDate getDueDate() {
        return dueDate;
    }
}
