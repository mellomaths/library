package com.mellomaths.library.domain.exception;

public class LoanDeadlineMustBeSetException extends RuntimeException {
    public LoanDeadlineMustBeSetException(String message) {
        super(message);
    }
}
