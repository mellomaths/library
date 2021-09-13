package com.mellomaths.library.domain;

import com.mellomaths.library.domain.core.Entity;

public class Patron extends Entity {

    private PatronType type;

    public Patron(String type) {
        super();
        this.type = PatronType.valueOf(type);
    }

    public PatronType getType() {
        return type;
    }

    public void setType(PatronType type) {
        this.type = type;
    }
}
