package com.mellomaths.library.domain.dto;

import com.mellomaths.library.domain.Loan;

public class LoanDto {

    protected String id;
    protected String creationDate;
    protected PatronDto patron;
    protected BookInstanceDto bookInstance;
    protected Integer daysToReturn;
    protected String dueDate;

    public LoanDto(String id, String creationDate, PatronDto patron, BookInstanceDto bookInstance, int daysToReturn, String dueDate) {
        this.id = id;
        this.creationDate = creationDate;
        this.patron = patron;
        this.bookInstance = bookInstance;
        this.daysToReturn = daysToReturn;
        this.dueDate = dueDate;
    }

    protected LoanDto(String creationDate, int daysToReturn, String dueDate) {
        this.creationDate = creationDate;
        this.daysToReturn = daysToReturn;
        this.dueDate = dueDate;
    }

    public static LoanDto fromModel(Loan loan) {
        return new LoanDto(
                loan.getId().toString(),
                loan.getCreationDate().toString(),
                PatronDto.fromModel(loan.getPatron()),
                BookInstanceDto.fromModel(loan.getBookInstance()),
                loan.getDaysToReturn(),
                loan.getDueDate().toString()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public PatronDto getPatron() {
        return patron;
    }

    public void setPatron(PatronDto patron) {
        this.patron = patron;
    }

    public BookInstanceDto getBookInstance() {
        return bookInstance;
    }

    public void setBookInstance(BookInstanceDto bookInstance) {
        this.bookInstance = bookInstance;
    }

    public Integer getDaysToReturn() {
        return daysToReturn;
    }

    public void setDaysToReturn(Integer daysToReturn) {
        this.daysToReturn = daysToReturn;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
