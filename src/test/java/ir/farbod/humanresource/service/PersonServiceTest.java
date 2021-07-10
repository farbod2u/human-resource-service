package ir.farbod.humanresource.service;

import ir.farbod.humanresource.repository.PersonRepository;
import ir.farbod.humanresource.repository.SchoolGradeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private SchoolGradeRepository schoolGradeRepository;

    private AutoCloseable autoCloseable;

    private PersonService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PersonService(personRepository, schoolGradeRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Disabled
    void save() {
    }

    @Test
    @Disabled
    void get() {
    }

    @Test
    @Disabled
    void getByIDNumber() {
    }

    @Test
    void getAll() {
        // when
        try {
            underTest.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then
        verify(personRepository).findAll();
    }
}