package com.mellomaths.library.domain.exception;

public class IsbnNotFoundException extends RuntimeException {
    public IsbnNotFoundException(String message) {
        super(message);
    }
}
