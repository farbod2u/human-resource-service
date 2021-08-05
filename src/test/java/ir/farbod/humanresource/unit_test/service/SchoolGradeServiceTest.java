package ir.farbod.humanresource.unit_test.service;

import ir.farbod.humanresource.unit_test.entity.SchoolGrade;
import ir.farbod.humanresource.unit_test.exception.EntityNotFoundException;
import ir.farbod.humanresource.unit_test.repository.SchoolGradeRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SchoolGradeServiceTest {

    @Mock
    private SchoolGradeRepository schoolGradeRepository;

    @InjectMocks
    private SchoolGradeService underTest;

    @BeforeEach
    void setUp() {
        underTest = new SchoolGradeService(schoolGradeRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Save SchoolGrade successfully")
    void save() {
        // given
        SchoolGrade schoolGrade = new SchoolGrade(
                0L,
                "first",
                null
        );

        // when
        underTest.save(schoolGrade);

        //then
        ArgumentCaptor<SchoolGrade> schoolGradeArgumentCaptor = ArgumentCaptor.forClass(SchoolGrade.class);
        verify(schoolGradeRepository).save(schoolGradeArgumentCaptor.capture());
        SchoolGrade capturedSchoolGrade = schoolGradeArgumentCaptor.getValue();

        assertThat(capturedSchoolGrade).isEqualTo(schoolGrade);
    }

    @SneakyThrows
    @Test
    @DisplayName("GetAll SchoolGrades successfully")
    void getAll() {
        // given
        given(schoolGradeRepository.findAll()).willReturn(List.of(new SchoolGrade()));

        // when
        underTest.getAll();

        // then
        verify(schoolGradeRepository).findAll();
    }

    @SneakyThrows
    @Test
    @DisplayName("GetAll SchoolGrades UN-successfully")
    void getAll2() {
        // given
        given(schoolGradeRepository.findAll()).willReturn(List.of());

        // when

        // then
        assertThatThrownBy(() -> underTest.getAll())
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("SchoolGrades not found.");
    }

    @SneakyThrows
    @Test
    @DisplayName("Get SchoolGrade by ID successfully")
    void get() {
        // given
        given(schoolGradeRepository.findById(any())).willReturn(Optional.of(new SchoolGrade()));

        // when
        underTest.get(any());

        // then
        verify(schoolGradeRepository).findById(any());
    }

    @SneakyThrows
    @Test
    @DisplayName("Get SchoolGrade by ID UN-successfully")
    void get2() {
        // given
        long id = 1L;
        given(schoolGradeRepository.findById(id)).willReturn(Optional.empty());

        // when

        // then
        assertThatThrownBy(() -> underTest.get(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("SchoolGrade with ID = " + id + " not found.");
    }

}