package com.mellomaths.library.domain;

public enum PatronType {
    REGULAR("REGULAR"),
    RESEARCHER("RESEARCHER");

    private final String value;

    PatronType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
