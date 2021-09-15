package com.mellomaths.library.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.mellomaths.library.domain.Patron;
import com.mellomaths.library.domain.PatronType;
import com.mellomaths.library.domain.utils.ValidateEnum;

import javax.validation.constraints.NotBlank;

public class NewPatronDto {

    @NotBlank
    @ValidateEnum(enumType = PatronType.class, message = "Patron type is not permitted.")
    private String type;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NewPatronDto(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
