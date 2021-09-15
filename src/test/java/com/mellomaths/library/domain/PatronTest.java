package com.mellomaths.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.mellomaths.library.domain.dto.NewPatronDto;
import com.mellomaths.library.domain.dto.PatronDto;
import com.mellomaths.library.domain.exception.BookNotAvailableForLoanException;
import com.mellomaths.library.domain.exception.InvalidLoanDeadlineException;
import com.mellomaths.library.domain.repository.PatronRepository;
import com.mellomaths.library.domain.usecase.CreateNewPatron;
import com.mellomaths.library.mocks.PatronInMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PatronTest {

    PatronRepository patronRepository;

    @BeforeEach
    public void setupRepository() {
        patronRepository = new PatronInMemoryRepository();
    }

    @Test
    @DisplayName("New Regular Patron")
    public void instantiateNewRegularPatron() {
        Patron patron = new Patron("REGULAR");
        assertNotNull(patron.getId());
        assertNotNull(patron.getCreationDate());
        assertEquals(PatronType.REGULAR, patron.getType());
        assertTrue(patron.isRegular());
        assertFalse(patron.isResearcher());
    }

    @Test
    @DisplayName("New Researcher Patron")
    public void instantiateNewResearcherPatron() {
        Patron patron = new Patron("RESEARCHER");
        assertNotNull(patron.getId());
        assertNotNull(patron.getCreationDate());
        assertEquals(PatronType.RESEARCHER, patron.getType());
        assertFalse(patron.isRegular());
        assertTrue(patron.isResearcher());
    }

    @Test
    @DisplayName("UseCase: Create New Patron - New Regular Patron")
    public void createNewRegularPatron() {
        NewPatronDto newPatronDto = new NewPatronDto("REGULAR");
        CreateNewPatron createNewPatron = new CreateNewPatron(patronRepository);
        PatronDto patron = createNewPatron.execute(newPatronDto);
        assertNotNull(patron.getId());
        assertNotNull(patron.getCreationDate());
        assertEquals("REGULAR", patron.getType());
    }

    @Test
    @DisplayName("UseCase: Create New Patron - New Researcher Patron")
    public void createNewResearcherPatron() {
        NewPatronDto newPatronDto = new NewPatronDto("RESEARCHER");
        CreateNewPatron createNewPatron = new CreateNewPatron(patronRepository);
        PatronDto patron = createNewPatron.execute(newPatronDto);
        assertNotNull(patron.getId());
        assertNotNull(patron.getCreationDate());
        assertEquals("RESEARCHER", patron.getType());
    }

}
