package com.mellomaths.library.domain.dto;

import com.mellomaths.library.domain.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookDto {
    protected String id;
    protected String creationDate;
    protected String title;
    protected String price;
    protected String isbn;
    protected List<BookInstanceDto> instances;

    public BookDto(String id, String creationDate, String title, String price, String isbn, List<BookInstanceDto> instances) {
        this.id = id;
        this.creationDate = creationDate;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
        this.instances = instances;
    }

    protected BookDto(String creationDate, String title, String price, String isbn) {
        this.creationDate = creationDate;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<BookInstanceDto> getInstances() {
        return instances;
    }

    public void setInstances(List<BookInstanceDto> instances) {
        this.instances = instances;
    }

    public static BookDto fromModel(Book book) {
        List<BookInstanceDto> instances = book.getInstances()
                .stream().map(BookInstanceDto::fromModel)
                .collect(Collectors.toList());
        return new BookDto(
                book.getId().toString(),
                book.getCreationDate().toString(),
                book.getTitle(),
                book.getPrice().toString(),
                book.getIsbn(),
                instances
        );
    }
}
