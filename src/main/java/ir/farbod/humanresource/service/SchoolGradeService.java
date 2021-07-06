package ir.farbod.humanresource.service;

import ir.farbod.humanresource.entity.SchoolGrade;
import ir.farbod.humanresource.repository.SchoolGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolGradeService {

    @Autowired
    private SchoolGradeRepository repository;

    public SchoolGrade save(SchoolGrade entity) {
        return repository.save(entity);
    }

    public List<SchoolGrade> getAll() {
        return repository.findAll();
    }

    public SchoolGrade get(Long id) {
        return repository.getById(id);

    }
}
