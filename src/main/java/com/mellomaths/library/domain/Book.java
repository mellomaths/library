package com.mellomaths.library.domain;

import com.mellomaths.library.domain.core.Entity;
import com.mellomaths.library.domain.dto.BookDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Book extends Entity {

    private final String title;
    private final BigDecimal price;
    private final String isbn;
    private final List<BookInstance> instances;

    public Book(String title, String price, String isbn) {
        super();
        this.title = title;
        this.price = new BigDecimal(price);
        this.isbn = isbn;
        this.instances = new ArrayList<>();
    }

    private Book(String id, String creationDate, String title, String price, String isbn, List<BookInstance> instances) {
        super(id, creationDate);
        this.title = title;
        this.price = new BigDecimal(price);
        this.isbn = isbn;
        this.instances = instances;
    }

    public static Book fromDto(BookDto bookDto) {
        List<BookInstance> instances = bookDto.getInstances()
                .stream().map(BookInstance::fromDto)
                .collect(Collectors.toList());
        return new Book(
                bookDto.getId(),
                bookDto.getCreationDate(),
                bookDto.getTitle(),
                bookDto.getPrice(),
                bookDto.getIsbn(),
                instances
        );
    }

    public BookInstance createNewInstance(String type) {
        BookInstance instance = new BookInstance(id.toString(), isbn, type);
        instances.add(instance);
        return instance;
    }

    public boolean canBeLoanedTo(Patron patron) {
        return instances.stream().anyMatch(instance -> instance.canBeLoanedTo(patron));
    }

    public Optional<BookInstance> getAvailableInstance(Patron patron) {
        return instances.stream().filter(instance -> instance.isAvailableFor(patron)).findFirst();
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<BookInstance> getInstances() {
        return instances;
    }
}
