package ir.farbod.humanresource.service;

import ir.farbod.humanresource.entity.Person;
import ir.farbod.humanresource.exception.EntityNotFoundException;
import ir.farbod.humanresource.exception.RequestException;
import ir.farbod.humanresource.repository.PersonRepository;
import ir.farbod.humanresource.repository.SchoolGradeRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private SchoolGradeRepository schoolGradeRepository;
    private PersonService underTest;

    //private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        //autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PersonService(personRepository, schoolGradeRepository);
    }

    @AfterEach
    void tearDown() {
        //autoCloseable.close();
    }

    @Test
    @DisplayName("Save with not already exists ID Number.")
    void save() {
        // given
        Person person = new Person(
                0L,
                "Saeed",
                "Safaeian",
                "0123456789",
                null
        );

        // when
        underTest.save(person);

        // then
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personRepository).save(personArgumentCaptor.capture());
        Person capturedPerson = personArgumentCaptor.getValue();

        assertThat(capturedPerson).isEqualTo(person);
    }

    @Test
    @DisplayName("Save with already EXISTS ID Number")
    void save2() {
        // given
        Person person = new Person(
                0L,
                "Saeed",
                "Safaeian",
                "0123456789",
                null
        );

        given(personRepository.findByIDNumber(anyString())).willReturn(java.util.Optional.of(new Person()));

        // when
        // then
        assertThatThrownBy(() -> underTest.save(person))
                .isInstanceOf(RequestException.class)
                .hasMessageContaining("ID Number " + person.getIdNumber() + " already exists.");
        verify(personRepository, never()).save(any());
    }

    @Test
    @Disabled
    void get() {
    }

    @Test
    @Disabled
    void getByIDNumber() {
    }

    @SneakyThrows
    @Test
    @DisplayName("Get all persons.")
    void getAll() {
        // given
        ArrayList<Person> t = new ArrayList<>();
        t.add(new Person());
        given(personRepository.findAll()).willReturn(
                t
        );

        // when
        underTest.getAll();

        // then
        verify(personRepository).findAll();
    }

    @SneakyThrows
    @Test
    @DisplayName("Get all persons, but there are not persons.")
    void getAll2() {
        // given

        // when

        // then
        assertThatThrownBy(() -> underTest.getAll())
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Data not found.");

    }
}