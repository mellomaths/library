package com.mellomaths.library.domain;

import com.mellomaths.library.domain.exception.BookNotAvailableForLoanException;
import com.mellomaths.library.domain.exception.InvalidLoanDeadlineException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoanTest {

    private Date calculateDueDate(int days) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        date = calendar.getTime();
        return date;
    }

    @Test
    @DisplayName("New Loan of Circulating Book Instance to Regular Patron")
    public void newLoanOfCirculatingBookInstanceToRegularPatron() {
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        BookInstance instance = book.createNewInstance("CIRCULATING");
        Patron patron = new Patron("REGULAR");
        Loan loan = patron.createLoan(book, 50);

        assertNotNull(loan.getId());
        assertNotNull(loan.getCreationDate());
        assertTrue(instance.isLoaned());
        assertEquals(book.getId().toString(), loan.getBookId());

        Date date = calculateDueDate(50);
        assertEquals(date.toString(), loan.getDueDate().getValue().toString());
    }

    @Test
    @DisplayName("New Loan of Circulating Book Instance to Researcher Patron")
    public void newLoanOfCirculatingBookInstanceToResearcherPatron() {
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        BookInstance instance = book.createNewInstance("CIRCULATING");
        Patron patron = new Patron("RESEARCHER");
        Loan loan = patron.createLoan(book, 50);

        assertNotNull(loan.getId());
        assertNotNull(loan.getCreationDate());
        assertTrue(instance.isLoaned());
        assertEquals(book.getId().toString(), loan.getBookId());

        Date date = calculateDueDate(50);
        assertEquals(date.toString(), loan.getDueDate().getValue().toString());
    }

    @Test
    @DisplayName("New Loan of Restricted Book Instance to Researcher Patron")
    public void newLoanOfRestrictedBookInstanceToResearcherPatron() {
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        BookInstance instance = book.createNewInstance("RESTRICTED");
        Patron patron = new Patron("RESEARCHER");
        Loan loan = patron.createLoan(book, 50);

        assertNotNull(loan.getId());
        assertNotNull(loan.getCreationDate());
        assertTrue(instance.isLoaned());
        assertEquals(book.getId().toString(), loan.getBookId());

        Date date = calculateDueDate(50);
        assertEquals(date.toString(), loan.getDueDate().getValue().toString());
    }

    @Test
    @DisplayName("Only Researchers can create a Loan with no deadline set")
    public void onlyResearchersCanCreateLoanWithNoDeadlineSet() {
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        BookInstance instance = book.createNewInstance("CIRCULATING");

        Patron regularPatron = new Patron("REGULAR");
        assertFalse(regularPatron.isDaysToReturnLoanValid(0));
        assertThrows(InvalidLoanDeadlineException.class, () -> regularPatron.createLoan(book, 0));

        Patron researcher = new Patron("RESEARCHER");
        assertTrue(researcher.isDaysToReturnLoanValid(0));
        assertTrue(researcher.isDaysToReturnLoanValid(null));

        Loan loanWithZeroDaysToReturn = researcher.createLoan(book, 0);
        Date date = calculateDueDate(Loan.LIMIT_TIME_IN_DAYS);
        assertNotNull(loanWithZeroDaysToReturn.getId());
        assertNotNull(loanWithZeroDaysToReturn.getCreationDate());
        assertTrue(instance.isLoaned());
        assertEquals(book.getId().toString(), loanWithZeroDaysToReturn.getBookId());
        assertEquals(date.toString(), loanWithZeroDaysToReturn.getDueDate().getValue().toString());

        instance = book.createNewInstance("CIRCULATING");
        Loan loanWithNullDaysToReturn = researcher.createLoan(book, null);
        assertNotNull(loanWithNullDaysToReturn.getId());
        assertNotNull(loanWithNullDaysToReturn.getCreationDate());
        assertTrue(instance.isLoaned());
        assertEquals(book.getId().toString(), loanWithNullDaysToReturn.getBookId());
        assertEquals(date.toString(), loanWithNullDaysToReturn.getDueDate().getValue().toString());
    }

    @Test
    @DisplayName("Nobody can create a loan with loan time greater than the limit")
    public void nobodyCanCreateLoanWithDeadlineGreaterThanLimit() {
        int limitPlusOne = Loan.LIMIT_TIME_IN_DAYS + 1;

        Book book = new Book("Design Patterns", "89.95", "9788573076103");

        Patron regularPatron = new Patron("REGULAR");
        assertFalse(regularPatron.isDaysToReturnLoanValid(limitPlusOne));
        assertThrows(InvalidLoanDeadlineException.class, () -> regularPatron.createLoan(book, limitPlusOne));

        Patron researcher = new Patron("RESEARCHER");
        assertFalse(researcher.isDaysToReturnLoanValid(limitPlusOne));
        assertThrows(InvalidLoanDeadlineException.class, () -> researcher.createLoan(book, limitPlusOne));
    }

    @Test
    @DisplayName("Nobody can create a loan for a book with no instances")
    public void nobodyCanCreateLoanForBookWithNoInstances() {
        Book book = new Book("Design Patterns", "89.95", "9788573076103");

        Patron regularPatron = new Patron("REGULAR");
        assertThrows(BookNotAvailableForLoanException.class, () -> regularPatron.createLoan(book, 50));

        Patron researcher = new Patron("RESEARCHER");
        assertThrows(BookNotAvailableForLoanException.class, () -> researcher.createLoan(book, 50));
    }

    @Test
    @DisplayName("Nobody can create a loan for a book with no available instances")
    public void nobodyCanCreateLoanForBookWithAvailableInstances() {
        Book book = new Book("Design Patterns", "89.95", "9788573076103");
        book.createNewInstance("CIRCULATING");
        Patron regularPatron = new Patron("REGULAR");
        regularPatron.createLoan(book, 50);

        assertThrows(BookNotAvailableForLoanException.class, () -> regularPatron.createLoan(book, 50));

        Patron researcher = new Patron("RESEARCHER");
        assertThrows(BookNotAvailableForLoanException.class, () -> researcher.createLoan(book, 50));
    }
}
