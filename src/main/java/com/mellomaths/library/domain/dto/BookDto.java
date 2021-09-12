package com.mellomaths.library.domain.dto;

import com.mellomaths.library.domain.Book;

public class BookDto {
    protected String id;
    protected String creationDate;
    protected String title;
    protected String price;
    protected String isbn;

    public BookDto(String id, String creationDate, String title, String price, String isbn) {
        this.id = id;
        this.creationDate = creationDate;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
    }

    public BookDto(String creationDate, String title, String price, String isbn) {
        this.creationDate = creationDate;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
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

    public static BookDto fromModel(Book book) {
        return new BookDto(
                book.getId().toString(),
                book.getCreationDate().toString(),
                book.getTitle(),
                book.getPrice().toString(),
                book.getIsbn());
    }
}
