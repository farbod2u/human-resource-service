package ir.farbod.humanresource.service;

import ir.farbod.humanresource.entity.Person;
import ir.farbod.humanresource.entity.PersonSchoolGrade;
import ir.farbod.humanresource.entity.SchoolGrade;
import ir.farbod.humanresource.exception.EntityNotFoundException;
import ir.farbod.humanresource.repository.PersonRepository;
import ir.farbod.humanresource.repository.SchoolGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private SchoolGradeRepository schoolGradeRepository;

    public Person save(Person entity) {
        try {
            List<PersonSchoolGrade> schoolGrades = entity.getPersonSchoolGrades();
            if (schoolGrades != null) {
                entity.setPersonSchoolGrades(new ArrayList<>());
                schoolGrades.forEach(p -> {
                    Optional<SchoolGrade> p2 = schoolGradeRepository.findById(p.getSchoolGrade().getId());
                    if (p2.isPresent()) {
                        var p3 = new PersonSchoolGrade();
                        p3.setPerson(entity);
                        p3.setSchoolGrade(p2.get());
                        p3.setGraduateDate(p.getGraduateDate());
                        entity.getPersonSchoolGrades().add(p3);
                    }
                });
            }

            Person res = repository.save(entity);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Person get(Long id) {
        Optional<Person> byId = repository.findById(id);
        if (byId.isPresent())
            return byId.get();
        else
            throw new EntityNotFoundException(id);
    }

    public List<Person> getAll() {
        try {
            List<Person> all = repository.findAll();
            if (all == null || all.isEmpty())
                throw new EntityNotFoundException("Data not found.");
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
