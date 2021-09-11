package com.mellomaths.library.infrastructure.out.mongo.book;

import com.mellomaths.library.domain.dto.BookDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "book")
public class BookDocument extends BookDto {

    @Id
    private final String id;
    public BookDocument(String id, String creationDate, String title, String price, String isbn) {
        super(creationDate, title, price, isbn);
        this.id = id;
    }

    public static BookDocument from(BookDto bookDto) {
        return new BookDocument(
                bookDto.getId(),
                bookDto.getCreationDate(),
                bookDto.getTitle(),
                bookDto.getPrice(),
                bookDto.getIsbn());
    }

    public BookDto toDto() {
        return new BookDto(id, creationDate, title, price, isbn);
    }
}
