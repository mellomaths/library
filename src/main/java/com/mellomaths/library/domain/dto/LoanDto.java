package com.mellomaths.library.domain.dto;

import com.mellomaths.library.domain.Loan;

public class LoanDto {

    protected String id;
    protected String creationDate;
    protected String patronId;
    protected String bookId;
    protected String bookInstanceId;
    protected int days;
    protected String dueDate;


    public LoanDto(String id, String creationDate, String patronId, String bookId, String bookInstanceId, int days, String dueDate) {
        this.id = id;
        this.creationDate = creationDate;
        this.patronId = patronId;
        this.bookId = bookId;
        this.bookInstanceId = bookInstanceId;
        this.days = days;
        this.dueDate = dueDate;
    }

    protected LoanDto(String creationDate, String patronId, String bookId, String bookInstanceId, int days, String dueDate) {
        this.creationDate = creationDate;
        this.patronId = patronId;
        this.bookId = bookId;
        this.bookInstanceId = bookInstanceId;
        this.days = days;
        this.dueDate = dueDate;
    }

    public static LoanDto fromModel(Loan loan) {
        return new LoanDto(
                loan.getId().toString(),
                loan.getCreationDate().toString(),
                loan.getPatronId(),
                loan.getBookId(),
                loan.getBookInstanceId(),
                loan.getDays(),
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

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
