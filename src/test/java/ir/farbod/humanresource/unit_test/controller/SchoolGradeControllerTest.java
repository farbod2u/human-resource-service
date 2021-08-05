package ir.farbod.humanresource.unit_test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.farbod.humanresource.unit_test.entity.SchoolGrade;
import ir.farbod.humanresource.unit_test.service.SchoolGradeService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SchoolGradeController.class)
class SchoolGradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SchoolGradeService service;

//    @Autowired
//    private SchoolGradeController underTest;

//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

    @Test
    @SneakyThrows
    void save() {
        // given
        SchoolGrade schoolGrade = new SchoolGrade(1L, "first", null);
        given(service.save(any(schoolGrade.getClass()))).willReturn(schoolGrade);
        String url = "/schoolgrade/save";

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(schoolGrade)))
                .andExpect(status().isCreated())
                .andReturn();
        String actualResult = mvcResult.getResponse().getContentAsString();
        String expectedResult = objectMapper.writeValueAsString(schoolGrade);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @SneakyThrows
    @Test
    void getAll() {
        // given
        List<SchoolGrade> list = List.of(
                new SchoolGrade(1L, "first", null),
                new SchoolGrade(2L, "second", null)
        );
        given(service.getAll()).willReturn(list);
        //Mockito.when(service.getAll()).thenReturn(list);
        String url = "/schoolgrade";

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();
        String expectedResult = objectMapper.writeValueAsString(list);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @SneakyThrows
    void get() {
        // given
        long id = 1L;
        SchoolGrade schoolGrade = new SchoolGrade(id, "first", null);
        given(service.get(anyLong())).willReturn(schoolGrade);

        String url = "/schoolgrade/" + id;

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andReturn();
        String actualResult = mvcResult.getResponse().getContentAsString();
        String expectedResul = objectMapper.writeValueAsString(schoolGrade);

        // then
        assertThat(actualResult).isEqualTo(expectedResul);
    }
}