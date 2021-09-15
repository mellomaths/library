package com.mellomaths.library.domain;

public enum PatronType {
    REGULAR("REGULAR") {
        @Override
        boolean acceptLoanTime(Integer daysToReturn) {
            return daysToReturn != null && daysToReturn > 0 && daysToReturn <= Loan.LIMIT_TIME_IN_DAYS;
        }
    },
    RESEARCHER("RESEARCHER") {
        @Override
        boolean acceptLoanTime(Integer daysToReturn) {
            if (daysToReturn == null || daysToReturn == 0) return true;

            return daysToReturn <= Loan.LIMIT_TIME_IN_DAYS;
        }
    };

    private final String value;

    PatronType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    abstract boolean acceptLoanTime(Integer daysToReturn);
}
