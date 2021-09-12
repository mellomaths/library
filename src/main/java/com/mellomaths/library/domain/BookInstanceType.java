package com.mellomaths.library.domain;

public enum BookInstanceType {
    RESTRICTED("RESTRICTED"),
    CIRCULATING("CIRCULATING");

    private final String value;

    BookInstanceType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
