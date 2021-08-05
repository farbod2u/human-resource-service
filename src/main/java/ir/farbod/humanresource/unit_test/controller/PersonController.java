package ir.farbod.humanresource.unit_test.controller;

import ir.farbod.humanresource.unit_test.entity.Person;
import ir.farbod.humanresource.unit_test.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/{personId}")
    public Person get(@PathVariable("personId") Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<Person> getAll() throws Exception {
        return service.getAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Person> save(@RequestBody Person entity) throws Exception {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @GetMapping("/getbyidnumber/{idnumber}")
    public Person getByIDNumber(@PathVariable("idnumber") String idNumber) {
        return service.getByIDNumber(idNumber);
    }

    @PutMapping("/update")
    public Person update(@RequestBody Person entity) throws Exception {
        return service.update(entity);
    }
}
