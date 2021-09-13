package com.mellomaths.library.domain;

import com.mellomaths.library.domain.core.Entity;
import com.mellomaths.library.domain.dto.BookInstanceDto;

public class BookInstance extends Entity {

    private String isbn;
    private BookInstanceType type;

    public BookInstance(String isbn, String type) {
        super();
        this.isbn = isbn;
        this.type = BookInstanceType.valueOf(type);
    }

    public BookInstance(String id, String creationDate, String isbn, String type) {
        super(id, creationDate);
        this.isbn = isbn;
        this.type = BookInstanceType.valueOf(type);
    }

    public static BookInstance fromDto(BookInstanceDto bookInstanceDto) {
        return new BookInstance(
                bookInstanceDto.getId(),
                bookInstanceDto.getCreationDate(),
                bookInstanceDto.getIsbn(),
                bookInstanceDto.getType()
        );
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

    public boolean isRestricted() {
        return type.equals(BookInstanceType.RESTRICTED);
    }

    public boolean isCirculating() {
        return type.equals(BookInstanceType.CIRCULATING);
    }
}
