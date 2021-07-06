package ir.farbod.humanresource.repository;

import ir.farbod.humanresource.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
