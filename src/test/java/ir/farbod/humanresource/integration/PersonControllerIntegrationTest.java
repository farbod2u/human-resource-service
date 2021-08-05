package ir.farbod.humanresource.integration;

import ir.farbod.humanresource.entity.Person;
import ir.farbod.humanresource.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
    void setup(){

    }

    @AfterEach
    void tearDown(){
        personRepository.deleteAll();
    }

    @Test
    @Sql("/Person.sql")
    public void get(){
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
    public void getAll(){
        // given
        String url = "/person";

        // when
        ResponseEntity<Person[]> response = testRestTemplate.getForEntity(url, Person[].class);

        // then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
