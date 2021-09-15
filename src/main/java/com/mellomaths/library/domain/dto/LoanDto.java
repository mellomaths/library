package com.mellomaths.library.domain.dto;

import com.mellomaths.library.domain.Loan;

public class LoanDto {

    protected String id;
    protected String creationDate;
    protected String patronId;
    protected String bookId;
    protected String bookInstanceId;
    protected Integer daysToReturn;
    protected String dueDate;


    public LoanDto(String id, String creationDate, String patronId, String bookId, String bookInstanceId, int daysToReturn, String dueDate) {
        this.id = id;
        this.creationDate = creationDate;
        this.patronId = patronId;
        this.bookId = bookId;
        this.bookInstanceId = bookInstanceId;
        this.daysToReturn = daysToReturn;
        this.dueDate = dueDate;
    }

    protected LoanDto(String creationDate, String patronId, String bookId, String bookInstanceId, int daysToReturn, String dueDate) {
        this.creationDate = creationDate;
        this.patronId = patronId;
        this.bookId = bookId;
        this.bookInstanceId = bookInstanceId;
        this.daysToReturn = daysToReturn;
        this.dueDate = dueDate;
    }

    public static LoanDto fromModel(Loan loan) {
        return new LoanDto(
                loan.getId().toString(),
                loan.getCreationDate().toString(),
                loan.getPatronId(),
                loan.getBookId(),
                loan.getBookInstanceId(),
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

    public String getPatronId() {
        return patronId;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookInstanceId() {
        return bookInstanceId;
    }

    public void setBookInstanceId(String bookInstanceId) {
        this.bookInstanceId = bookInstanceId;
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
