package com.mellomaths.library.infrastructure.out.mongo.book;

import com.mellomaths.library.domain.dto.BookInstanceDto;
import org.springframework.data.annotation.Id;

public class BookInstanceDocument extends BookInstanceDto {
    @Id
    private final String id;
    public BookInstanceDocument(String id, String creationDate, String isbn, String type) {
        super(creationDate, isbn, type);
        this.id = id;
    }

    public static BookInstanceDocument from(BookInstanceDto bookInstanceDto) {
        return new BookInstanceDocument(
                bookInstanceDto.getId(),
                bookInstanceDto.getCreationDate(),
                bookInstanceDto.getIsbn(),
                bookInstanceDto.getType()
        );
    }

    public BookInstanceDto toDto() {
        return new BookInstanceDto(id, creationDate, isbn, type);
    }
}
