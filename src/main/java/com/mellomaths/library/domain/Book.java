package com.mellomaths.library.domain;

import com.mellomaths.library.domain.core.Entity;

import java.math.BigDecimal;

public class Book extends Entity {

    private String title;
    private BigDecimal price;
    private String isbn;

    public Book(String title, String price, String isbn) {
        super();
        this.title = title;
        this.price = new BigDecimal(price);
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
