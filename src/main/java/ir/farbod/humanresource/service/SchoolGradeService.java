package ir.farbod.humanresource.service;

import ir.farbod.humanresource.entity.SchoolGrade;
import ir.farbod.humanresource.exception.EntityNotFoundException;
import ir.farbod.humanresource.repository.SchoolGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolGradeService {

    private final SchoolGradeRepository repository;

    @Autowired
    public SchoolGradeService(SchoolGradeRepository repository) {
        this.repository = repository;
    }

    public SchoolGrade save(SchoolGrade entity) {
        return repository.save(entity);
    }

    public List<SchoolGrade> getAll() throws Exception {
        List<SchoolGrade> all = repository.findAll();
        if (all.isEmpty())
            throw new EntityNotFoundException("SchoolGrades not found.");
        return all;
    }

    public SchoolGrade get(Long id) throws Exception {
        Optional<SchoolGrade> byId = repository.findById(id);
        if (byId.isEmpty())
            throw new EntityNotFoundException("SchoolGrade with ID = " + id + " not found.");
        return byId.get();
    }
}
