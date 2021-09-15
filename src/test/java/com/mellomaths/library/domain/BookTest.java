package com.mellomaths.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.dto.BookInstanceDto;
import com.mellomaths.library.domain.dto.NewBookDto;
import com.mellomaths.library.domain.dto.NewBookInstanceDto;
import com.mellomaths.library.domain.exception.IsbnDuplicatedException;
import com.mellomaths.library.domain.exception.IsbnNotFoundException;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.domain.repository.BookRepository;
import com.mellomaths.library.domain.usecase.CreateNewBook;
import com.mellomaths.library.domain.usecase.CreateNewBookInstance;
import com.mellomaths.library.domain.usecase.SearchBookByISBN;
import com.mellomaths.library.mocks.BookInMemoryRepository;
import com.mellomaths.library.mocks.BookInstanceInMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.Optional;

public class BookTest {

    BookRepository bookRepository;
    BookInstanceRepository bookInstanceRepository;

    @BeforeEach
    public void setupRepository() {
        bookRepository = new BookInMemoryRepository();
        bookInstanceRepository = new BookInstanceInMemoryRepository();
    }

    @Test
    @DisplayName("New Book")
    public void instantiateNewBook() {
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        assertNotNull(book.getId());
        assertNotNull(book.getCreationDate());
        assertEquals("Design Patterns", book.getTitle());
        assertEquals("89.95", book.getPrice().toString());
        assertEquals("9788573076103", book.getIsbn());
    }

    @Test
    @DisplayName("UseCase: Create New Book - Success")
    public void createNewBook() {
        NewBookDto newBookDto = new NewBookDto("Design Patterns", "89.95", "9788573076103");
        CreateNewBook createNewBook = new CreateNewBook(bookRepository);
        BookDto book = createNewBook.execute(newBookDto);
        assertNotNull(book.getId());
        assertNotNull(book.getCreationDate());
        assertEquals("Design Patterns", book.getTitle());
        assertEquals("89.95", book.getPrice());
        assertEquals("9788573076103", book.getIsbn());
    }

    @Test
    @DisplayName("UseCase: Create New Book - Attempt to create book with duplicated ISBN")
    public void createBookWithDuplicatedIsbn() {
        NewBookDto newBookDto = new NewBookDto("Design Patterns", "89.95", "9788573076103");
        CreateNewBook createNewBook = new CreateNewBook(bookRepository);
        createNewBook.execute(newBookDto);
        assertThrows(IsbnDuplicatedException.class, () -> createNewBook.execute(newBookDto));
    }

    @Test
    @DisplayName("New Book Restricted Instance")
    public void createNewRestrictedInstance() {
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        BookInstance instance = book.createNewInstance("RESTRICTED");
        assertNotNull(book.getId());
        assertNotNull(book.getCreationDate());
        assertEquals(1, book.getInstances().size());

        assertNotNull(instance.getId());
        assertNotNull(instance.getCreationDate());
        assertEquals(instance.getBookId(), book.getId().toString());
        assertEquals(instance.getIsbn(), book.getIsbn());
        assertEquals(instance.getType(), BookInstanceType.RESTRICTED);
        assertTrue(instance.isRestricted());
    }

    @Test
    @DisplayName("New Book Circulating Instance")
    public void createNewCirculatingInstance() {
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        BookInstance instance = book.createNewInstance("CIRCULATING");
        assertNotNull(book.getId());
        assertNotNull(book.getCreationDate());
        assertEquals(1, book.getInstances().size());

        assertNotNull(instance.getId());
        assertNotNull(instance.getCreationDate());
        assertEquals(instance.getBookId(), book.getId().toString());
        assertEquals(instance.getIsbn(), book.getIsbn());
        assertEquals(instance.getType(), BookInstanceType.CIRCULATING);
        assertTrue(instance.isCirculating());
    }

    @Test
    @DisplayName("UseCase: Create New Book Instance - Success")
    public void createNewInstance() {
        NewBookDto newBookDto = new NewBookDto("Design Patterns", "89.95", "9788573076103");
        CreateNewBook createNewBook = new CreateNewBook(bookRepository);
        BookDto book = createNewBook.execute(newBookDto);
        NewBookInstanceDto newBookInstanceDto = new NewBookInstanceDto("9788573076103", "RESTRICTED");
        CreateNewBookInstance createNewBookInstance = new CreateNewBookInstance(bookRepository, bookInstanceRepository);
        BookInstanceDto instance = createNewBookInstance.execute(newBookInstanceDto);
        assertNotNull(book.getId());
        assertNotNull(book.getCreationDate());

        assertNotNull(instance.getId());
        assertNotNull(instance.getCreationDate());
        assertEquals(instance.getBookId(), book.getId());
        assertEquals(instance.getIsbn(), book.getIsbn());
        assertEquals(instance.getType(), "RESTRICTED");
    }

    @Test
    @DisplayName("UseCase: Create New Book Instance - Attempt to create an instance for a Book ISBN not registered")
    public void createNewInstanceWithIsbnNotFound() {
        NewBookInstanceDto newBookInstanceDto = new NewBookInstanceDto("9788576082675", "RESTRICTED");
        CreateNewBookInstance createNewBookInstance = new CreateNewBookInstance(bookRepository, bookInstanceRepository);
        assertThrows(IsbnNotFoundException.class, () -> createNewBookInstance.execute(newBookInstanceDto));
    }

    @Test
    @DisplayName("UseCase: Search Book By ISBN - Find book by its ISBN")
    public void searchBookByISBN() {
        NewBookDto newBookDto = new NewBookDto("Design Patterns", "89.95", "9788573076103");
        new CreateNewBook(bookRepository).execute(newBookDto);

        BookDto book = new SearchBookByISBN(bookRepository, bookInstanceRepository).execute(newBookDto.getIsbn());
        assertNotNull(book.getId());
        assertNotNull(book.getCreationDate());
        assertEquals("Design Patterns", book.getTitle());
        assertEquals("89.95", book.getPrice());
        assertEquals("9788573076103", book.getIsbn());
    }

    @Test
    @DisplayName("Book with no instances cannot be loaned")
    public void bookWithNoInstancesCannotBeLoaned() {
        Book book = new Book("Design Patterns", "89.95", "9788573076103");

        Patron regularPatron = new Patron("REGULAR");
        assertFalse(book.canBeLoanedTo(regularPatron));

        Patron researcher = new Patron("RESEARCHER");
        assertFalse(book.canBeLoanedTo(researcher));

        Optional<BookInstance> availableInstanceForRegularPatron = book.getAvailableInstance(regularPatron);
        assertTrue(availableInstanceForRegularPatron.isEmpty());

        Optional<BookInstance> availableInstanceForResearcherPatron = book.getAvailableInstance(researcher);
        assertTrue(availableInstanceForResearcherPatron.isEmpty());
    }

    @Test
    @DisplayName("Restricted Book Instances can be loaned only to Researchers")
    public void restrictedBookInstancesOnlyForResearchers() {
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        book.createNewInstance("RESTRICTED");

        Patron regularPatron = new Patron("REGULAR");
        assertFalse(book.canBeLoanedTo(regularPatron));

        Patron researcher = new Patron("RESEARCHER");
        assertTrue(book.canBeLoanedTo(researcher));

        Optional<BookInstance> availableInstanceForRegularPatron = book.getAvailableInstance(regularPatron);
        assertTrue(availableInstanceForRegularPatron.isEmpty());

        Optional<BookInstance> availableInstanceForResearcherPatron = book.getAvailableInstance(researcher);
        assertFalse(availableInstanceForResearcherPatron.isEmpty());
    }

    @Test
    @DisplayName("Circulating Book Instances can be loaned to any Patron")
    public void circulatingInstancesForAnyPatron() {
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        book.createNewInstance("CIRCULATING");

        Patron regularPatron = new Patron("REGULAR");
        assertTrue(book.canBeLoanedTo(regularPatron));

        Patron researcher = new Patron("RESEARCHER");
        assertTrue(book.canBeLoanedTo(researcher));

        Optional<BookInstance> availableInstanceForRegularPatron = book.getAvailableInstance(regularPatron);
        assertFalse(availableInstanceForRegularPatron.isEmpty());

        Optional<BookInstance> availableInstanceForResearcherPatron = book.getAvailableInstance(researcher);
        assertFalse(availableInstanceForResearcherPatron.isEmpty());
    }
}
