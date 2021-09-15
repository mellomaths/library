package com.mellomaths.library.domain;

public enum PatronType {
    REGULAR("REGULAR") {
        @Override
        boolean acceptLoanTime(int days) {
            return days > 0 && days <= Loan.LIMIT_TIME_IN_DAYS;
        }
    },
    RESEARCHER("RESEARCHER") {
        @Override
        boolean acceptLoanTime(int days) {
            if (days == 0) return true;

            return days <= Loan.LIMIT_TIME_IN_DAYS;
        }
    };

    private final String value;

    PatronType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    abstract boolean acceptLoanTime(int days);
}
