package ir.farbod.humanresource.repository;

import ir.farbod.humanresource.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @Test
    void findByIDNumber() {
        // given
        String idNumber = "0123456789";
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
}