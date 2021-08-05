package ir.farbod.humanresource.unit_test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.farbod.humanresource.unit_test.entity.Person;
import ir.farbod.humanresource.unit_test.service.PersonService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonService service;

    @Test
    @SneakyThrows
    void get() {
        // given
        long id = 1L;
        Person person = new Person(id, "First Name", "Last Name", "ID Number", null);

        given(service.get(anyLong())).willReturn(person);

        String url = "/person/" + id;

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();
        String expectedResult = objectMapper.writeValueAsString(person);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @SneakyThrows
    @Test
    void getAll() {
        // given
        List<Person> list = List.of(
                new Person(1L, "f1", "l1", "id#1", null),
                new Person(2L, "f2", "l2", "id#2", null)
        );

        given(service.getAll()).willReturn(list);

        String url = "/person";

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();
        String expectedResult = objectMapper.writeValueAsString(list);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @Test
    @SneakyThrows
    void save() {
        // given
        Person person = new Person(1L, "First Name", "Last Name", "ID Number", null);

        given(service.save(person)).willReturn(person);

        String url = "/person/save";

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isCreated())
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();
        String expectedResult = objectMapper.writeValueAsString(person);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @SneakyThrows
    @Test
    void getByIDNumber() {
        // given
        String idNumber = "0123456789";
        Person person = new Person(1L, "First Name", "Last Name", idNumber, null);

        given(service.getByIDNumber(anyString())).willReturn(person);

        String url = "/person/getbyidnumber/" + idNumber;

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();
        String expectedResult = objectMapper.writeValueAsString(person);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @SneakyThrows
    void update() {
        // given
        Person person = new Person(1L, "First Name", "Last Name", "ID Number", null);

        given(service.update(any())).willReturn(person);

        String url = "/person/update";

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();
        String expectedResult = objectMapper.writeValueAsString(person);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);

    }
}