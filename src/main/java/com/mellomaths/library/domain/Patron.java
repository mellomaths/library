package com.mellomaths.library.domain;

import com.mellomaths.library.domain.core.Entity;
import com.mellomaths.library.domain.dto.PatronDto;

public class Patron extends Entity {

    private PatronType type;

    public Patron(String type) {
        super();
        this.type = PatronType.valueOf(type);
    }

    public Patron(String id, String creationDate, String type) {
        super(id, creationDate);
        this.type = PatronType.valueOf(type);
    }

    public static Patron fromDto(PatronDto patronDto) {
        return new Patron(patronDto.getId(), patronDto.getCreationDate(), patronDto.getType());
    }

    public PatronType getType() {
        return type;
    }

    public void setType(PatronType type) {
        this.type = type;
    }

    public boolean isRegular() {
        return type.equals(PatronType.REGULAR);
    }

    public boolean isResearcher() {
        return type.equals(PatronType.RESEARCHER);
    }
}
