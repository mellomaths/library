package com.mellomaths.library.infrastructure.out.mongo.patron;

import com.mellomaths.library.domain.dto.PatronDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patron")
public class PatronDocument extends PatronDto {
    @Id
    private final String id;

    public PatronDocument(String id, String creationDate, String type) {
        super(creationDate, type);
        this.id = id;
    }

    public static PatronDocument from(PatronDto patronDto) {
        return new PatronDocument(
                patronDto.getId(),
                patronDto.getCreationDate(),
                patronDto.getType()
        );
    }

    public PatronDto toDto() {
        return new PatronDto(id, creationDate, type);
    }
}
