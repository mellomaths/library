package com.mellomaths.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.mellomaths.library.domain.dto.NewPatronDto;
import com.mellomaths.library.domain.dto.PatronDto;
import com.mellomaths.library.domain.repository.PatronRepository;
import com.mellomaths.library.domain.usecase.CreateNewPatron;
import com.mellomaths.library.mocks.PatronInMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PatronTest {

    PatronRepository patronRepository;

    @BeforeEach
    public void setupRepository() {
        patronRepository = new PatronInMemoryRepository();
    }

    @Test
    public void instantiateNewPatronOfTypeRegular() {
        Patron patron = new Patron("REGULAR");
        assertNotNull(patron.getId());
        assertNotNull(patron.getCreationDate());
        assertEquals(PatronType.REGULAR, patron.getType());
    }

    @Test
    public void instantiateNewPatronOfTypeResearcher() {
        Patron patron = new Patron("RESEARCHER");
        assertNotNull(patron.getId());
        assertNotNull(patron.getCreationDate());
        assertEquals(PatronType.RESEARCHER, patron.getType());
    }

    @Test
    public void instantiateNewPatronFromDto() {
        Patron patron = new NewPatronDto("REGULAR").toModel();
        assertNotNull(patron.getId());
        assertNotNull(patron.getCreationDate());
    }

    @Test
    public void createNewPatronOfTypeRegular() {
        NewPatronDto newPatronDto = new NewPatronDto("REGULAR");
        CreateNewPatron createNewPatron = new CreateNewPatron(patronRepository);
        PatronDto patron = createNewPatron.execute(newPatronDto);
        assertNotNull(patron.getId());
        assertNotNull(patron.getCreationDate());
        assertEquals("REGULAR", patron.getType());
    }

    @Test
    public void createNewPatronOfTypeResearcher() {
        NewPatronDto newPatronDto = new NewPatronDto("RESEARCHER");
        CreateNewPatron createNewPatron = new CreateNewPatron(patronRepository);
        PatronDto patron = createNewPatron.execute(newPatronDto);
        assertNotNull(patron.getId());
        assertNotNull(patron.getCreationDate());
        assertEquals("RESEARCHER", patron.getType());
    }

}
