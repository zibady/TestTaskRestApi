package net.zibady.task.kindgeek_test.service;

import net.zibady.task.kindgeek_test.entity.Department;
import net.zibady.task.kindgeek_test.entity.Person;
import net.zibady.task.kindgeek_test.entity.Position;
import net.zibady.task.kindgeek_test.entity.Project;
import net.zibady.task.kindgeek_test.exception.PersonNotFoundException;
import net.zibady.task.kindgeek_test.exception.PositionNotFoundException;
import net.zibady.task.kindgeek_test.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public void updatePerson (Long id, Person newPerson) {
        Optional<Person> personOptional = personRepository.findById(id);
        Person person;

        if (!personOptional.isPresent()) {
            throw new PersonNotFoundException("Person with id - " + id + " doesn't exist");
        } else {
            person = personOptional.get();

            if (newPerson.getName() != null && !newPerson.getName().isEmpty()) {
                person.setName(newPerson.getName());
            }
            if (newPerson.getPosition() != null) {
                Position position = positionService.getPosition(newPerson.getPosition().getId());
                Set<Position> positions = person.getDepartment().getPositions();
                if (!positions.contains(position)) {
                    throw new PositionNotFoundException(
                            String.format("Position with id - %d doesn't exist in Department - %s",
                                    position.getId(), newPerson.getDepartment().getName()));
                }
                person.setPosition(position);
            }
            if (newPerson.getProject() != null) {
                Project project = projectService.getProject(newPerson.getProject().getId());
                person.setProject(project);
            }

            personRepository.save(person);
        }
    }

    public void deletePerson (Long id) {
        Optional<Person> person = personRepository.findById(id);

        if (!person.isPresent())
            throw new PersonNotFoundException("Person with id - " + id + " doesn't exist");

        personRepository.deleteById(id);
    }

    public Person getPerson (Long id) {
        Optional<Person> person = personRepository.findById(id);

        if (!person.isPresent())
            throw new PersonNotFoundException("Person with id - " + id + " doesn't exist");

        return person.get();
    }

    public List<Person> getAllPeople () {
        return personRepository.findAll();
    }
}
