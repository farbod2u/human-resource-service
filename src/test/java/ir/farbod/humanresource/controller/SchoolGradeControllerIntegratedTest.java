package ir.farbod.humanresource.controller;

import ir.farbod.humanresource.entity.SchoolGrade;
import ir.farbod.humanresource.repository.SchoolGradeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SchoolGradeControllerIntegratedTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private SchoolGradeRepository schoolGradeRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        schoolGradeRepository.deleteAll();
    }

    @Test
    @Sql("/SchoolGrade.sql")
    public void get() {
        // given

//        SchoolGrade schoolGrade1 = new SchoolGrade(1L, "first", null);
//        schoolGradeRepository.save(schoolGrade1);
//        SchoolGrade schoolGrade2 = new SchoolGrade(2L, "second", null);
//        schoolGradeRepository.save(schoolGrade2);

        long id = 1;
        String url = "/schoolgrade/" + id;

        // when
        ResponseEntity<SchoolGrade> response = testRestTemplate.getForEntity(url, SchoolGrade.class);

        SchoolGrade result = response.getBody();

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    @Sql("/SchoolGrade.sql")
    public void getAll() {
        // given
        String url = "/schoolgrade";

        // when
        ResponseEntity<SchoolGrade[]> response = testRestTemplate.getForEntity(url, SchoolGrade[].class);

        // then
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void save(){
        // given
        String name = "first";
        SchoolGrade schoolGrade = new SchoolGrade(null, name, null);
        HttpEntity<SchoolGrade> requestBody = new HttpEntity<>(schoolGrade);

        String url =  "/schoolgrade/save";

        // when
        ResponseEntity<SchoolGrade> response = testRestTemplate.postForEntity(url, requestBody, SchoolGrade.class);
        SchoolGrade result = response.getBody();

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(name);

    }

}
