package com.mellomaths.library.domain;

public enum BookInstanceType {
    RESTRICTED("RESTRICTED") {
        @Override
        boolean acceptPatron(Patron patron) {
            return patron.isResearcher();
        }
    },
    CIRCULATING("CIRCULATING") {
        @Override
        boolean acceptPatron(Patron patron) {
            return true;
        }
    };

    private final String value;

    BookInstanceType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    abstract boolean acceptPatron(Patron patron);
}
