package ir.farbod.humanresource.unit_test.controller;

import ir.farbod.humanresource.unit_test.entity.SchoolGrade;
import ir.farbod.humanresource.unit_test.service.SchoolGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schoolgrade")
public class SchoolGradeController {

    private final SchoolGradeService service;

    @Autowired
    public SchoolGradeController(SchoolGradeService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<SchoolGrade> save(@RequestBody SchoolGrade entity) {
        return new ResponseEntity<>(service.save(entity),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SchoolGrade>> getAll() throws Exception {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public SchoolGrade get(@PathVariable("id") Long schoolGradeId) throws Exception {
        return service.get(schoolGradeId);
    }
}
