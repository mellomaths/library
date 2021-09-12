package com.mellomaths.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.dto.BookInstanceDto;
import com.mellomaths.library.domain.dto.NewBookDto;
import com.mellomaths.library.domain.dto.NewBookInstanceDto;
import com.mellomaths.library.domain.exception.IsbnNotFoundException;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.domain.repository.BookRepository;
import com.mellomaths.library.domain.usecase.CreateNewBook;
import com.mellomaths.library.domain.usecase.CreateNewBookInstance;
import com.mellomaths.library.mocks.BookInMemoryRepository;
import com.mellomaths.library.mocks.BookInstanceInMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookInstanceTest {

    BookRepository bookRepository;
    BookInstanceRepository bookInstanceRepository;

    @BeforeEach
    public void setupRepository() {
        bookInstanceRepository = new BookInstanceInMemoryRepository();
        bookRepository = new BookInMemoryRepository();
        NewBookDto newBookDto = new NewBookDto("Design Patterns", "89.95", "9788573076103");
        CreateNewBook createNewBook = new CreateNewBook(bookRepository);
        BookDto book = createNewBook.execute(newBookDto);
    }

    @Test
    public void instantiateNewBookInstance() {
        BookInstance bookInstance = new BookInstance("9788573076103", "CIRCULATING");
        assertNotNull(bookInstance.getId());
        assertNotNull(bookInstance.getCreationDate());
    }

    @Test
    public void instantiateNewBookInstanceFromDto() {
        BookInstance bookInstance = new NewBookInstanceDto("9788573076103", "CIRCULATING").toModel();
        assertNotNull(bookInstance.getId());
        assertNotNull(bookInstance.getCreationDate());
    }

    @Test
    public void createNewBookInstanceOfTypeCirculating() {
        NewBookInstanceDto newBookInstanceDto = new NewBookInstanceDto("9788573076103", "CIRCULATING");
        CreateNewBookInstance createNewBookInstance = new CreateNewBookInstance(bookRepository, bookInstanceRepository);
        BookInstanceDto bookInstance = createNewBookInstance.execute(newBookInstanceDto);
        assertNotNull(bookInstance.getId());
        assertNotNull(bookInstance.getCreationDate());
        assertEquals("9788573076103", bookInstance.getIsbn());
        assertEquals("CIRCULATING", bookInstance.getType());
    }

    @Test
    public void createNewBookInstanceOfTypeRestricted() {
        NewBookInstanceDto newBookInstanceDto = new NewBookInstanceDto("9788573076103", "RESTRICTED");
        CreateNewBookInstance createNewBookInstance = new CreateNewBookInstance(bookRepository, bookInstanceRepository);
        BookInstanceDto bookInstance = createNewBookInstance.execute(newBookInstanceDto);
        assertNotNull(bookInstance.getId());
        assertNotNull(bookInstance.getCreationDate());
        assertEquals("9788573076103", bookInstance.getIsbn());
        assertEquals("RESTRICTED", bookInstance.getType());
    }

    @Test
    public void createNewBookInstanceWithIsbnNotFound() {
        NewBookInstanceDto newBookInstanceDto = new NewBookInstanceDto("9788576082675", "RESTRICTED");
        CreateNewBookInstance createNewBookInstance = new CreateNewBookInstance(bookRepository, bookInstanceRepository);
        assertThrows(IsbnNotFoundException.class, () -> createNewBookInstance.execute(newBookInstanceDto));
    }

}
