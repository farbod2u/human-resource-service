package ir.farbod.humanresource.controller;

import ir.farbod.humanresource.entity.SchoolGrade;
import ir.farbod.humanresource.service.SchoolGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schoolgrade")
public class SchoolGradeController {

    @Autowired
    private SchoolGradeService service;

    @PostMapping("/save")
    public SchoolGrade save(@RequestBody SchoolGrade entity) throws Exception {
        return service.save(entity);
    }

    @GetMapping
    public List<SchoolGrade> getAll() throws Exception {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SchoolGrade get(@PathVariable("id") Long schoolGradeId) throws Exception {
        return service.get(schoolGradeId);
    }
}
