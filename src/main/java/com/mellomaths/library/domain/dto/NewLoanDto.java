package com.mellomaths.library.domain.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

public class NewLoanDto {

    @NotBlank
    private String patronId;

    @Range(min = 1, max = 60)
    private Integer daysToReturn;

    public NewLoanDto(String patronId) {
        this.patronId = patronId;
    }

    public NewLoanDto(String patronId, Integer daysToReturn) {
        this.patronId = patronId;
        this.daysToReturn = daysToReturn;
    }

    public String getPatronId() {
        return patronId;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public Integer getDaysToReturn() {
        return daysToReturn;
    }

    public void setDaysToReturn(Integer daysToReturn) {
        this.daysToReturn = daysToReturn;
    }
}
