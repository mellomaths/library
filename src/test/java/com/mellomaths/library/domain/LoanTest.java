package com.mellomaths.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.mellomaths.library.domain.dto.*;
import com.mellomaths.library.domain.exception.*;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.domain.repository.BookRepository;
import com.mellomaths.library.domain.repository.LoanRepository;
import com.mellomaths.library.domain.repository.PatronRepository;
import com.mellomaths.library.domain.usecase.CreateNewBook;
import com.mellomaths.library.domain.usecase.CreateNewBookInstance;
import com.mellomaths.library.domain.usecase.CreateNewLoan;
import com.mellomaths.library.domain.usecase.CreateNewPatron;
import com.mellomaths.library.mocks.BookInMemoryRepository;
import com.mellomaths.library.mocks.BookInstanceInMemoryRepository;
import com.mellomaths.library.mocks.LoanInMemoryRepository;
import com.mellomaths.library.mocks.PatronInMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LoanTest {

    BookRepository bookRepository;
    BookInstanceRepository bookInstanceRepository;
    PatronRepository patronRepository;
    LoanRepository loanRepository;

    @BeforeEach
    public void setupRepository() {
        bookInstanceRepository = new BookInstanceInMemoryRepository();
        bookRepository = new BookInMemoryRepository();
        patronRepository = new PatronInMemoryRepository();
        loanRepository = new LoanInMemoryRepository();
    }

    private Book createNewBook(String title, String price, String isbn) {
        NewBookDto newBookDto = new NewBookDto(title, price, isbn);
        CreateNewBook createNewBook = new CreateNewBook(bookRepository);
        BookDto bookDto = createNewBook.execute(newBookDto);
        return Book.fromDto(bookDto);
    }

    private BookInstance createNewBookInstance(String isbn, String type) {
        NewBookInstanceDto newBookInstanceDto = new NewBookInstanceDto(isbn, type);
        CreateNewBookInstance createNewBookInstance = new CreateNewBookInstance(bookRepository, bookInstanceRepository);
        BookInstanceDto bookInstanceDto = createNewBookInstance.execute(newBookInstanceDto);
        return BookInstance.fromDto(bookInstanceDto);
    }

    private Patron createNewPatron(String type) {
        NewPatronDto newPatronDto = new NewPatronDto(type);
        CreateNewPatron createNewPatron = new CreateNewPatron(patronRepository);
        PatronDto patronDto = createNewPatron.execute(newPatronDto);
        return Patron.fromDto(patronDto);
    }

    @Test
    public void createNewLoan() {
        CreateNewLoan createNewLoan = new CreateNewLoan(bookRepository, bookInstanceRepository, patronRepository, loanRepository);
        Book book = createNewBook("Design Patterns", "89.95", "9788573076103");
        createNewBookInstance("9788573076103", "RESTRICTED");
        BookInstance instance = createNewBookInstance("9788573076103", "CIRCULATING");
        Patron patron = createNewPatron("REGULAR");
        LoanDto loanDto = createNewLoan.execute(book.getId().toString(), new NewLoanDto(patron.getId().toString(), 5));

        assertNotNull(loanDto.getId());
        assertNotNull(loanDto.getCreationDate());
        assertEquals(patron.getId().toString(), loanDto.getPatronId());
        assertEquals(book.getId().toString(), loanDto.getBookId());
        assertEquals(instance.getId().toString(), loanDto.getBookInstanceId());
        assertEquals(5, loanDto.getDays());
    }

    @Test
    public void bookNotFound() {
        Patron patron = new Patron("REGULAR");
        Book book = null;
        List<BookInstance> instances = new ArrayList<>();
        BookInstance restrictedInstance = new BookInstance("9788573076103", "RESTRICTED");
        instances.add(restrictedInstance);

        assertThrows(BookNotFoundException.class, () -> new Loan(patron, book, instances, 0));
    }

    @Test
    public void patronNotFound() {
        Patron patron = null;
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        List<BookInstance> instances = new ArrayList<>();
        BookInstance restrictedInstance = new BookInstance("9788573076103", "RESTRICTED");
        instances.add(restrictedInstance);

        assertThrows(PatronNotFoundException.class, () -> new Loan(patron, book, instances, 0));
    }

    @Test
    public void regularPatronWithDeadlineGreaterThanLimit() {
        Patron patron = new Patron("REGULAR");
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        List<BookInstance> instances = new ArrayList<>();
        BookInstance circulatingInstance = new BookInstance("9788573076103", "CIRCULATING");
        instances.add(circulatingInstance);
        BookInstance restrictedInstance = new BookInstance("9788573076103", "RESTRICTED");
        instances.add(restrictedInstance);

        assertThrows(InvalidLoanDeadlineException.class, () -> new Loan(patron, book, instances, 61));
    }

    @Test
    public void researcherPatronWithDeadlineGreaterThanLimit() {
        Patron patron = new Patron("RESEARCHER");
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        List<BookInstance> instances = new ArrayList<>();
        BookInstance circulatingInstance = new BookInstance("9788573076103", "CIRCULATING");
        instances.add(circulatingInstance);
        BookInstance restrictedInstance = new BookInstance("9788573076103", "RESTRICTED");
        instances.add(restrictedInstance);

        assertThrows(InvalidLoanDeadlineException.class, () -> new Loan(patron, book, instances, 61));
    }

    @Test
    public void regularPatronWithoutDeadline() {
        Patron patron = new Patron("REGULAR");
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        List<BookInstance> instances = new ArrayList<>();
        BookInstance circulatingInstance = new BookInstance("9788573076103", "CIRCULATING");
        instances.add(circulatingInstance);
        BookInstance restrictedInstance = new BookInstance("9788573076103", "RESTRICTED");
        instances.add(restrictedInstance);

        assertThrows(LoanDeadlineMustBeSetException.class, () -> new Loan(patron, book, instances, 0));
    }

    @Test
    public void researcherPatronWithoutDeadline() {
        Patron patron = new Patron("RESEARCHER");
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        List<BookInstance> instances = new ArrayList<>();
        BookInstance restrictedInstance = new BookInstance("9788573076103", "RESTRICTED");
        instances.add(restrictedInstance);

        Loan loan = new Loan(patron, book, instances, 0);
        assertEquals(patron.getId().toString(), loan.getPatronId());
        assertEquals(book.getId().toString(), loan.getBookId());
        assertEquals(restrictedInstance.getId().toString(), loan.getBookInstanceId());
        assertEquals(60, loan.getDays());
    }

    @Test
    public void regularPatronWithDeadline() {
        Patron patron = new Patron("REGULAR");
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        List<BookInstance> instances = new ArrayList<>();
        BookInstance circulatingInstance = new BookInstance("9788573076103", "CIRCULATING");
        instances.add(circulatingInstance);

        Loan loan = new Loan(patron, book, instances, 50);
        assertEquals(patron.getId().toString(), loan.getPatronId());
        assertEquals(book.getId().toString(), loan.getBookId());
        assertEquals(circulatingInstance.getId().toString(), loan.getBookInstanceId());
        assertEquals(50, loan.getDays());
    }

    @Test
    public void researcherPatronWithDeadline() {
        Patron patron = new Patron("RESEARCHER");
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        List<BookInstance> instances = new ArrayList<>();
        BookInstance restrictedInstance = new BookInstance("9788573076103", "RESTRICTED");
        instances.add(restrictedInstance);

        Loan loan = new Loan(patron, book, instances, 50);
        assertEquals(patron.getId().toString(), loan.getPatronId());
        assertEquals(book.getId().toString(), loan.getBookId());
        assertEquals(restrictedInstance.getId().toString(), loan.getBookInstanceId());
        assertEquals(50, loan.getDays());
    }

    @Test
    public void bookInstancesNotFound() {
        Patron patron = new Patron("REGULAR");
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        List<BookInstance> instances = new ArrayList<>();

        assertThrows(BookNotAvailableForLoanException.class, () -> new Loan(patron, book, instances, 50));
    }

    @Test
    public void regularPatronWithOnlyRestrictedInstancesAvailable() {
        Patron patron = new Patron("REGULAR");
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        List<BookInstance> instances = new ArrayList<>();
        BookInstance restrictedInstance = new BookInstance("9788573076103", "RESTRICTED");
        instances.add(restrictedInstance);

        assertThrows(BookNotAvailableForLoanException.class, () -> new Loan(patron, book, instances, 50));
    }

    @Test
    public void regularPatronWithAllTypesOfInstancesAvailable() {
        Patron patron = new Patron("REGULAR");
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        List<BookInstance> instances = new ArrayList<>();
        BookInstance restrictedInstance = new BookInstance("9788573076103", "RESTRICTED");
        instances.add(restrictedInstance);
        BookInstance circulatingInstance = new BookInstance("9788573076103", "CIRCULATING");
        instances.add(circulatingInstance);

        Loan loan = new Loan(patron, book, instances, 50);
        assertEquals(patron.getId().toString(), loan.getPatronId());
        assertEquals(book.getId().toString(), loan.getBookId());
        assertEquals(circulatingInstance.getId().toString(), loan.getBookInstanceId());
        assertEquals(50, loan.getDays());
    }

    @Test
    public void researcherPatronWithOnlyRestrictedInstancesAvailable() {
        Patron patron = new Patron("RESEARCHER");
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        List<BookInstance> instances = new ArrayList<>();
        BookInstance restrictedInstance = new BookInstance("9788573076103", "RESTRICTED");
        instances.add(restrictedInstance);

        Loan loan = new Loan(patron, book, instances, 50);
        assertEquals(patron.getId().toString(), loan.getPatronId());
        assertEquals(book.getId().toString(), loan.getBookId());
        assertEquals(restrictedInstance.getId().toString(), loan.getBookInstanceId());
        assertEquals(50, loan.getDays());
    }

    @Test
    public void researcherPatronWithOnlyCirculatingInstancesAvailable() {
        Patron patron = new Patron("RESEARCHER");
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        List<BookInstance> instances = new ArrayList<>();
        BookInstance circulatingInstance = new BookInstance("9788573076103", "CIRCULATING");
        instances.add(circulatingInstance);

        Loan loan = new Loan(patron, book, instances, 50);
        assertEquals(patron.getId().toString(), loan.getPatronId());
        assertEquals(book.getId().toString(), loan.getBookId());
        assertEquals(circulatingInstance.getId().toString(), loan.getBookInstanceId());
        assertEquals(50, loan.getDays());
    }

    @Test
    public void researcherPatronWithAllTypesOfInstancesAvailable() {
        Patron patron = new Patron("RESEARCHER");
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        List<BookInstance> instances = new ArrayList<>();
        BookInstance restrictedInstance = new BookInstance("9788573076103", "RESTRICTED");
        instances.add(restrictedInstance);
        BookInstance circulatingInstance = new BookInstance("9788573076103", "CIRCULATING");
        instances.add(circulatingInstance);

        Loan loan = new Loan(patron, book, instances, 50);
        assertEquals(patron.getId().toString(), loan.getPatronId());
        assertEquals(book.getId().toString(), loan.getBookId());
        assertEquals(restrictedInstance.getId().toString(), loan.getBookInstanceId());
        assertEquals(50, loan.getDays());
    }

}
