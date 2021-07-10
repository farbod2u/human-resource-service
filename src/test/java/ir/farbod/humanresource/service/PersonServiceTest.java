package ir.farbod.humanresource.service;

import ir.farbod.humanresource.entity.Person;
import ir.farbod.humanresource.exception.RequestException;
import ir.farbod.humanresource.repository.PersonRepository;
import ir.farbod.humanresource.repository.SchoolGradeRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
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
    void tearDown() throws Exception {
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

        given(personRepository.findByIDNumber(person.getIdNumber())).willReturn(java.util.Optional.of(new Person()));

        // when
        // then
        assertThatThrownBy(() -> underTest.save(person))
                .isInstanceOf(RequestException.class)
                .hasMessageContaining("ID Number " + person.getIdNumber() + " already exists.");

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
            // do nothing
        }


        // then
        verify(personRepository).findAll();
    }
}