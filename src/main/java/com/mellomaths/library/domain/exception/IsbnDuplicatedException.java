package com.mellomaths.library.domain.exception;

public class IsbnDuplicatedException extends RuntimeException {
    public IsbnDuplicatedException(String message) {
        super(message);
    }
}
