package com.mellomaths.library.domain.dto;

import com.mellomaths.library.domain.BookInstance;

public class BookInstanceDto {

    protected String id;
    protected String creationDate;
    protected String isbn;
    protected String type;

    public BookInstanceDto(String id, String creationDate, String isbn, String type) {
        this.id = id;
        this.creationDate = creationDate;
        this.isbn = isbn;
        this.type = type;
    }

    public BookInstanceDto(String creationDate, String isbn, String type) {
        this.creationDate = creationDate;
        this.isbn = isbn;
        this.type = type;
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

    public static BookInstanceDto fromModel(BookInstance bookInstance) {
        return new BookInstanceDto(
                bookInstance.getId().toString(),
                bookInstance.getCreationDate().toString(),
                bookInstance.getIsbn(),
                bookInstance.getType().toString());
    }
}
