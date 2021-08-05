package ir.farbod.humanresource.unit_test.repository;

import ir.farbod.humanresource.unit_test.entity.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Test if should find person by its ID Number")
    void findByIDNumber() {
        // given
        String idNumber = "1123456789";
        Person person = new Person(
                0L,
                "SaeedTest",
                "SafaeianTest",
                idNumber,
                null);
        repository.save(person);

        // when
        boolean result = repository.findByIDNumber(idNumber).isPresent();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Test if shouldn't find person by its ID Number")
    void not_findByIDNumber() {
        // given
        String idNumber = "1123456789";

        // when
        boolean result = repository.findByIDNumber(idNumber).isPresent();

        // then
        assertThat(result).isFalse();
    }

}