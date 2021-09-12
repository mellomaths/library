package com.mellomaths.library.domain;

import com.mellomaths.library.domain.core.Entity;

public class BookInstance extends Entity {

    private String isbn;
    private BookInstanceType type;

    public BookInstance(String isbn, String type) {
        super();
        this.isbn = isbn;
        this.type = BookInstanceType.valueOf(type);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookInstanceType getType() {
        return type;
    }

    public void setType(BookInstanceType type) {
        this.type = type;
    }
}
