package com.mellomaths.library.infrastructure.out.mongo.book;

import com.mellomaths.library.domain.dto.BookDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "book")
public class BookDocument extends BookDto {

    @Id
    private final String id;

    @DBRef
    @Field("book_instances")
    private List<BookInstanceDocument> instances;

    public BookDocument(String id, String creationDate, String title, String price, String isbn, List<BookInstanceDocument> instances) {
        super(creationDate, title, price, isbn);
        this.id = id;
        this.instances = instances;
    }

    public static BookDocument from(BookDto bookDto) {
        return new BookDocument(
                bookDto.getId(),
                bookDto.getCreationDate(),
                bookDto.getTitle(),
                bookDto.getPrice(),
                bookDto.getIsbn(),
                bookDto.getInstances()
                        .stream().map(BookInstanceDocument::from)
                        .collect(Collectors.toList())
        );
    }

    public BookDto toDto() {
        return new BookDto(id, creationDate, title, price, isbn, instances.stream().map(BookInstanceDocument::toDto).collect(Collectors.toList()));
    }
}
