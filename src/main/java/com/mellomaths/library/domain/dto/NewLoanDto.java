package com.mellomaths.library.domain.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

public class NewLoanDto {

    @NotBlank
    private String patronId;

    @Range(min = 1, max = 60)
    private int days;

    public NewLoanDto(String patronId) {
        this.patronId = patronId;
    }

    public NewLoanDto(String patronId, int days) {
        this.patronId = patronId;
        this.days = days;
    }

    public String getPatronId() {
        return patronId;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
