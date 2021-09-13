package com.mellomaths.library.domain.exception;

public class BookNotAvailableForLoanException extends RuntimeException {
    public BookNotAvailableForLoanException(String message) {
        super(message);
    }
}
