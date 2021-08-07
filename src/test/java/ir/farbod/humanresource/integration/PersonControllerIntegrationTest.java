package ir.farbod.humanresource.integration;

import ir.farbod.humanresource.entity.Person;
import ir.farbod.humanresource.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void setup() {

    }

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
    }

    @Test
    @Sql("/Person.sql")
    public void get() {
        // given
        long id = 1L;
        String url = "/person/" + id;

        // when
        ResponseEntity<Person> response = testRestTemplate.getForEntity(url, Person.class);
        Person result = response.getBody();

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    @Sql("/Person.sql")
    public void getAll() {
        // given
        String url = "/person";

        // when
        ResponseEntity<Person[]> response = testRestTemplate.getForEntity(url, Person[].class);

        // then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void save() {
        // given
        Person person = new Person(null, "fn1", "ln1", "idno1", null);
        String url = "/person/save";

        HttpEntity<Person> request = new HttpEntity<>(person);

        // when
        ResponseEntity<Person> response = testRestTemplate.postForEntity(url, request, Person.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
    }

    @Test
    @Sql("/Person.sql")
    public void getByIDNumber() {
        // given
        String idNumber = "0123456789";
        String url = "/person/getbyidnumber/" + idNumber;

        // when
        ResponseEntity<Person> response = testRestTemplate.getForEntity(url, Person.class);

        // then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getIdNumber()).isEqualTo(idNumber);
    }

    @Test
    @Sql("/Person.sql")
    public void update() {
        // given
        Person person = new Person(1L,
                "fn2",
                "ln2",
                "idNo2",
                null);
        HttpEntity<Person> request = new HttpEntity<>(person);
        String url = "/person/update";

        // when
        ResponseEntity<Person> response = testRestTemplate.exchange(url, HttpMethod.PUT, request, Person.class);

        // then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(person.getId());
        assertThat(response.getBody().getFirstName()).isEqualTo(person.getFirstName());
    }

}
