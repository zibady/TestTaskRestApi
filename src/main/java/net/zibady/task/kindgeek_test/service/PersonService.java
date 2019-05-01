package net.zibady.task.kindgeek_test.service;

import net.zibady.task.kindgeek_test.entity.Department;
import net.zibady.task.kindgeek_test.entity.Person;
import net.zibady.task.kindgeek_test.entity.Position;
import net.zibady.task.kindgeek_test.entity.Project;
import net.zibady.task.kindgeek_test.exception.PersonNotFoundException;
import net.zibady.task.kindgeek_test.exception.PositionNotFoundException;
import net.zibady.task.kindgeek_test.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ProjectService projectService;
    private final PositionService positionService;
    private final DepartmentService departmentService;

    @Autowired
    public PersonService(PersonRepository personRepository, ProjectService projectService, PositionService positionService, DepartmentService departmentService) {
        this.personRepository = personRepository;
        this.projectService = projectService;
        this.positionService = positionService;
        this.departmentService = departmentService;
    }

    public Person getPerson (Long id) {

        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person with id - " + id + " doesn't exist"));
    }

    public List<Person> getAllPeople () {
        return personRepository.findAll();
    }

    public void addPerson (Person person) {

        Project project = projectService.getProject(person.getProject().getId());
        Department department = departmentService.getDepartment(person.getDepartment().getId());
        Position position = positionService.getPosition(person.getPosition().getId());

        Set<Position> positions = department.getPositions();
        if (!positions.contains(position)) {
            throw new PositionNotFoundException(
                    String.format("Position with id - %d doesn't exist in Department - %s",
                            position.getId(), department.getName()));
        }

        person.setProject(project);
        person.setDepartment(department);
        person.setPosition(position);
        personRepository.save(person);
    }
    // updatedPerson не може містити пусті поля, position відповідає певному department, валідація на фронті
    public void updatePerson (Person updatedPerson) {
        long id = updatedPerson.getId();
        Person person = personRepository.findById(id)
                        .orElseThrow(() -> new PersonNotFoundException("Person with id - " + id + " doesn't exist"));

        if (updatedPerson.getName() != null && !updatedPerson.getName().isEmpty()) {
            person.setName(updatedPerson.getName());
        }

        Project project = projectService.getProject(updatedPerson.getProject().getId());
        Department department = departmentService.getDepartment(updatedPerson.getDepartment().getId());
        Position position = positionService.getPosition(updatedPerson.getPosition().getId());

        person.setDepartment(department);

        Set<Position> positions = department.getPositions();
        if (!positions.contains(position)) {
            throw new PositionNotFoundException(
                    String.format("Position with id - %d doesn't exist in Department - %s",
                            position.getId(), updatedPerson.getDepartment().getName()));
        }
        person.setPosition(position);
        person.setProject(project);

        personRepository.save(person);
    }

    public void deletePerson (Long id) {
        try {
            personRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new PersonNotFoundException("Person with id - " + id + " doesn't exist");
        }
    }

}

