package ir.farbod.humanresource.controller;

import ir.farbod.humanresource.entity.Person;
import ir.farbod.humanresource.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/{personId}")
    public Person get(@PathVariable("personId") Long id){
        return service.get(id);
    }

    @GetMapping
    public List<Person> getAll(){
        return service.getAll();
    }

    @PostMapping("/save")
    public Person save(@RequestBody Person entity){
        return service.save(entity);
    }

}
