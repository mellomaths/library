package com.mellomaths.library.domain.exception;

public class LimitOfLoansPerPatronException extends RuntimeException {
    public LimitOfLoansPerPatronException(String message) {
        super(message);
    }
}
