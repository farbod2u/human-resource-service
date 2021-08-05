package ir.farbod.humanresource.repository;

import ir.farbod.humanresource.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("from Person where idNumber = ?1")
    public Optional<Person> findByIDNumber(String idNumber);
}
