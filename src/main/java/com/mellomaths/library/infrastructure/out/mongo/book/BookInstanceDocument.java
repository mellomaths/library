package com.mellomaths.library.infrastructure.out.mongo.book;

import com.mellomaths.library.domain.dto.BookInstanceDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book_instance")
public class BookInstanceDocument extends BookInstanceDto {
    @Id
    private final String id;

    public BookInstanceDocument(String id, String creationDate, String bookId, String isbn, String type) {
        super(creationDate, bookId, isbn, type);
        this.id = id;
    }

    public static BookInstanceDocument from(BookInstanceDto bookInstanceDto) {
        return new BookInstanceDocument(
                bookInstanceDto.getId(),
                bookInstanceDto.getCreationDate(),
                bookInstanceDto.getBookId(),
                bookInstanceDto.getIsbn(),
                bookInstanceDto.getType()
        );
    }

    public BookInstanceDto toDto() {
        return new BookInstanceDto(id, creationDate, bookId, isbn, type);
    }
}
