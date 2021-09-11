package com.mellomaths.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.dto.NewBookDto;
import com.mellomaths.library.domain.exception.IsbnDuplicatedException;
import com.mellomaths.library.domain.repository.BookRepository;
import com.mellomaths.library.domain.usecase.CreateNewBook;
import com.mellomaths.library.mocks.BookInMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {

    BookRepository repository;

    @BeforeEach
    public void setupRepository() {
        repository = new BookInMemoryRepository();
    }

    @Test
    public void instantiateNewBook() {
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        assertNotNull(book.getId());
        assertNotNull(book.getCreationDate());
    }

    @Test
    public void instantiateNewBookFromDto() {
        Book book = new NewBookDto("Design Patterns", "89.95", "9788573076103").toModel();
        assertNotNull(book.getId());
        assertNotNull(book.getCreationDate());
    }

    @Test
    public void createNewBook() {
        NewBookDto newBookDto = new NewBookDto("Design Patterns", "89.95", "9788573076103");
        CreateNewBook createNewBook = new CreateNewBook(repository);
        BookDto book = createNewBook.execute(newBookDto);
        assertNotNull(book.getId());
        assertNotNull(book.getCreationDate());
        assertEquals("Design Patterns", book.getTitle());
        assertEquals("89.95", book.getPrice());
        assertEquals("9788573076103", book.getIsbn());
    }

    @Test
    public void createBookWithDuplicatedIsbn() {
        NewBookDto newBookDto = new NewBookDto("Design Patterns", "89.95", "9788573076103");
        CreateNewBook createNewBook = new CreateNewBook(repository);
        createNewBook.execute(newBookDto);
        assertThrows(IsbnDuplicatedException.class, () -> createNewBook.execute(newBookDto));
    }
}
