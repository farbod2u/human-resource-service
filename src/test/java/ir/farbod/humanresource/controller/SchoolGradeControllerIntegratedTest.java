package ir.farbod.humanresource.controller;

import ir.farbod.humanresource.controller.SchoolGradeController;
import ir.farbod.humanresource.entity.SchoolGrade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {SchoolGradeController.class})
@AutoConfigureTestDatabase
public class SchoolGradeControllerIntegratedTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    //@Sql("/SchoolGrade.sq_")
    public void get() {
        // given
        String url = "/schoolgrade/1";
        testRestTemplate.getForEntity(url, SchoolGrade.class);
        // when
        //testRestTemplate.getForEntity(url);

        // then
    }
}
