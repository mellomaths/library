package com.mellomaths.library.domain.exception;

public class InvalidLoanDeadlineException extends RuntimeException {
    public InvalidLoanDeadlineException(String message) {
        super(message);
    }
}
