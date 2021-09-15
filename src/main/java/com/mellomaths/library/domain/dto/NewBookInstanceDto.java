package com.mellomaths.library.domain.dto;

import com.mellomaths.library.domain.BookInstance;
import com.mellomaths.library.domain.BookInstanceType;
import com.mellomaths.library.domain.utils.ValidateEnum;
import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.NotBlank;

public class NewBookInstanceDto {
    @NotBlank
    @ISBN
    private String isbn;

    @NotBlank
    @ValidateEnum(enumType = BookInstanceType.class, message = "Book Instance Type is not permitted.")
    private String type;

    public NewBookInstanceDto(String isbn, String type) {
        this.isbn = isbn;
        this.type = type;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
