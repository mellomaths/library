package com.mellomaths.library.domain.dto;

import com.mellomaths.library.domain.Book;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NewBookDto {

    @NotBlank
    protected String title;

    @Positive
    protected String price;

    @NotBlank
    @ISBN
    protected String isbn;

    public NewBookDto(String title, String price, String isbn) {
        this.title = title;
        this.price = price;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Book toModel() {
        return new Book(title, price, isbn);
    }
}
