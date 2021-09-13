package com.mellomaths.library.domain.dto;

import com.mellomaths.library.domain.Patron;

public class PatronDto {

    protected String id;
    protected String creationDate;
    protected String type;

    public PatronDto(String id, String creationDate, String type) {
        this.id = id;
        this.creationDate = creationDate;
        this.type = type;
    }

    public PatronDto(String creationDate, String type) {
        this.creationDate = creationDate;
        this.type = type;
    }

    public static PatronDto fromModel(Patron patron) {
        return new PatronDto(
                patron.getId().toString(),
                patron.getCreationDate().toString(),
                patron.getType().toString()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
