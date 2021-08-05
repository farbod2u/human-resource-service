package ir.farbod.humanresource.unit_test.repository;

import ir.farbod.humanresource.unit_test.entity.SchoolGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolGradeRepository extends JpaRepository<SchoolGrade, Long> {
}
