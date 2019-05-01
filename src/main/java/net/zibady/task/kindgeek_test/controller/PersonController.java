package net.zibady.task.kindgeek_test.controller;

import net.zibady.task.kindgeek_test.entity.Person;
import net.zibady.task.kindgeek_test.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> allEmployees() {

        return personService.getAllPeople();
    }

    @GetMapping("/{id}")
    public Person getEmployee(@PathVariable long id) {

        return personService.getPerson(id);
    }

    @PostMapping
    public void addEmployee(@RequestBody Person person) {

        personService.addPerson(person);
    }

    @PutMapping
    public void updateEmployee(@RequestBody Person person) {
        personService.updatePerson(person);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        personService.deletePerson(id);
    }
}
