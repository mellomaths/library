package com.mellomaths.library.domain;

import com.mellomaths.library.domain.core.Entity;
import com.mellomaths.library.domain.dto.BookInstanceDto;

public class BookInstance extends Entity {

    private String bookId;
    private String isbn;
    private BookInstanceType type;
    private boolean isLoaned;

    public BookInstance(String bookId, String isbn, String type) {
        super();
        this.bookId = bookId;
        this.isbn = isbn;
        this.type = BookInstanceType.valueOf(type);
        this.isLoaned = false;
    }

    private BookInstance(String id, String creationDate, String bookId, String isbn, String type) {
        super(id, creationDate);
        this.bookId = bookId;
        this.isbn = isbn;
        this.type = BookInstanceType.valueOf(type);
    }

    public static BookInstance fromDto(BookInstanceDto bookInstanceDto) {
        return new BookInstance(
                bookInstanceDto.getId(),
                bookInstanceDto.getCreationDate(),
                bookInstanceDto.getBookId(),
                bookInstanceDto.getIsbn(),
                bookInstanceDto.getType()
        );
    }

    public boolean isRestricted() {
        return type.equals(BookInstanceType.RESTRICTED);
    }

    public boolean isCirculating() {
        return type.equals(BookInstanceType.CIRCULATING);
    }

    public boolean canBeLoanedTo(Patron patron) {
        return type.acceptPatron(patron);
    }

    public boolean isAvailableFor(Patron patron) {
        return !this.isLoaned && this.canBeLoanedTo(patron);
    }

    public Loan createLoan(Patron patron, Integer daysToReturn) {
        this.isLoaned = true;
        return new Loan(patron, this, daysToReturn);
    }

    public String getBookId() {
        return bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookInstanceType getType() {
        return type;
    }

    public boolean isLoaned() {
        return isLoaned;
    }

}
