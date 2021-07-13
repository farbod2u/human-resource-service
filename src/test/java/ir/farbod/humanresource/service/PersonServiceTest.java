package ir.farbod.humanresource.service;

import ir.farbod.humanresource.entity.Person;
import ir.farbod.humanresource.entity.PersonSchoolGrade;
import ir.farbod.humanresource.entity.SchoolGrade;
import ir.farbod.humanresource.exception.EntityNotFoundException;
import ir.farbod.humanresource.exception.RequestException;
import ir.farbod.humanresource.repository.PersonRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private SchoolGradeService schoolGradeService;
    private PersonService underTest;

    //private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        //autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PersonService(personRepository, schoolGradeService);
    }

    @AfterEach
    void tearDown() {
        //autoCloseable.close();
    }

    @SneakyThrows
    @Test
    @DisplayName("Save with not already exists ID Number.")
    void save() {
        // given
        ArrayList<PersonSchoolGrade> personSchoolGrades = new ArrayList<>();
        personSchoolGrades.add(new PersonSchoolGrade(new Person(), new SchoolGrade(), null));

        Person person = new Person(
                0L,
                "Saeed",
                "Safaeian",
                "0123456789",
                personSchoolGrades
        );

        given(personRepository.findByIDNumber(any())).willReturn(Optional.empty());
        given(schoolGradeService.get(any())).willReturn(new SchoolGrade());

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
    @DisplayName("Get Person by id.")
    void get() {
        // given
        given(personRepository.findById(any())).willReturn(Optional.of(new Person()));

        // when
        underTest.get(any());

        // then
        verify(personRepository).findById(any());
    }

    @Test
    @DisplayName("Don't Get Person by id.")
    void get2() {
        // given
        given(personRepository.findById(any())).willReturn(Optional.empty());

        // when

        // then
        assertThatThrownBy(() -> underTest.get(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Entity with ID = ")
                .hasMessageContaining(" not found.");
    }

    @Test
    @DisplayName("Get Person by ID Number")
    void getByIDNumber() {
        // given
        given(personRepository.findByIDNumber(any())).willReturn(Optional.of(new Person()));

        // when
        underTest.getByIDNumber(any());

        // then
        verify(personRepository).findByIDNumber(any());
    }

    @Test
    @DisplayName("Don't Get Person by ID Number")
    void getByIDNumber2() {
        // given
        String idNumber = "123456789";
        given(personRepository.findByIDNumber(idNumber)).willReturn(Optional.empty());

        // when

        // then
        assertThatThrownBy(() -> underTest.getByIDNumber(idNumber))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Person with ID Number " + idNumber + " not found");
    }

    @SneakyThrows
    @Test
    @DisplayName("Get all persons.")
    void getAll() {
        // given
        given(personRepository.findAll()).willReturn(List.of(new Person()));

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
        given(personRepository.findAll()).willReturn(List.of());

        // when

        // then
        assertThatThrownBy(() -> underTest.getAll())
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Data not found.");

    }

    @SneakyThrows
    @Test
    @DisplayName("Update Person Successfully.")
    void update() {
        // given
        ArrayList<PersonSchoolGrade> personSchoolGrades = new ArrayList<>();
        SchoolGrade schoolGrade = new SchoolGrade();
        schoolGrade.setId(1L);
        schoolGrade.setName("first");
        personSchoolGrades.add(new PersonSchoolGrade(null, schoolGrade, LocalDate.now()));

        Person person = new Person(
                1L,
                "Saeed",
                "Safaeian",
                "0123456789",
                personSchoolGrades
        );

        given(personRepository.findById(person.getId())).willReturn(Optional.of(person));
        given(schoolGradeService.get(schoolGrade.getId())).willReturn(new SchoolGrade());

        // when
        Person result = underTest.update(person);

        // then
        assertThat(result).isEqualTo(person);
    }
}