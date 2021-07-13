package ir.farbod.humanresource.service;

import ir.farbod.humanresource.entity.Person;
import ir.farbod.humanresource.entity.PersonSchoolGrade;
import ir.farbod.humanresource.entity.SchoolGrade;
import ir.farbod.humanresource.exception.EntityNotFoundException;
import ir.farbod.humanresource.exception.RequestException;
import ir.farbod.humanresource.repository.PersonRepository;
import ir.farbod.humanresource.repository.SchoolGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final SchoolGradeRepository schoolGradeRepository;
    private final SchoolGradeService schoolGradeService;

    @Autowired
    public PersonService(PersonRepository repository, SchoolGradeRepository schoolGradeRepository, SchoolGradeService schoolGradeService) {
        this.repository = repository;
        this.schoolGradeRepository = schoolGradeRepository;
        this.schoolGradeService = schoolGradeService;
    }

    public Person save(Person entity) throws Exception {
        Optional<Person> byIDNumber = repository.findByIDNumber(entity.getIdNumber());
        if (byIDNumber.isPresent())
            throw new RequestException("ID Number " + entity.getIdNumber() + " already exists.");
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

        return repository.save(entity);
    }

    public Person get(Long id) {
        Optional<Person> byId = repository.findById(id);
        if (byId.isPresent())
            return byId.get();
        else
            throw new EntityNotFoundException(id);
    }

    public Person getByIDNumber(String idNumber) {
        Optional<Person> byIDNumber = repository.findByIDNumber(idNumber);
        if (byIDNumber.isEmpty())
            throw new EntityNotFoundException("Person with ID Number " + idNumber + " not found");
        return byIDNumber.get();
    }

    public List<Person> getAll() throws Exception {
        List<Person> all = repository.findAll();
        if (all.isEmpty())
            throw new EntityNotFoundException("Data not found.");
        return all;
    }

    @Transactional(rollbackOn = {Exception.class})
    public Person update(Person entity) throws Exception {
        Person person = get(entity.getId());
        person.setFirstName(entity.getFirstName());
        person.setLastName(entity.getLastName());
        person.setIdNumber(entity.getIdNumber());

        SchoolGrade schoolGrade = schoolGradeService.get(entity.getPersonSchoolGrades().get(0).getSchoolGrade().getId());
        PersonSchoolGrade personSchoolGrade = new PersonSchoolGrade();
        personSchoolGrade.setPerson(person);
        personSchoolGrade.setSchoolGrade(schoolGrade);
        personSchoolGrade.setGraduateDate(entity.getPersonSchoolGrades().get(0).getGraduateDate());

        person.getPersonSchoolGrades().add(personSchoolGrade);

        return person;
    }

}
