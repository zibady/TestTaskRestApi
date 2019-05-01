package net.zibady.task.kindgeek_test.service;

import net.zibady.task.kindgeek_test.entity.Department;
import net.zibady.task.kindgeek_test.entity.Position;
import net.zibady.task.kindgeek_test.exception.DepartmentNotFoundException;
import net.zibady.task.kindgeek_test.exception.PositionException;
import net.zibady.task.kindgeek_test.exception.PositionNotFoundException;
import net.zibady.task.kindgeek_test.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    private final PositionRepository positionRepository;
    private final DepartmentService departmentService;

    @Autowired
    public PositionService(PositionRepository positionRepository, DepartmentService departmentService) {
        this.positionRepository = positionRepository;
        this.departmentService = departmentService;
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }


    public Position getPosition(long id) {
        Optional<Position> position = positionRepository.findById(id);

        if (!position.isPresent())
            throw new PositionNotFoundException("Position with id - " + id + " doesn't exist");

        return position.get();
    }

    public void addPosition(Position position) {

        if (position.getName() == null || position.getName().isEmpty())
            throw new PositionException("Position name can't be empty\n");
        if (position.getDepartment() == null )
            throw new PositionException("Position should belong to department. Department name can't be empty\n");

        Department department = departmentService.getDepartment(position.getDepartment().getId()); //todo repo logger
        if (!departmentService.getAllDepartments().contains(department)) {
            throw new DepartmentNotFoundException(String.format("Position should belong to department. " +
                                                "Department with id - %d doesn't exist", position.getDepartment().getId()));
        }
        position.setDepartment(department);

        positionRepository.save(position);

    }

    public void updatePosition(long id, Position newPosition) {
        Optional<Position> positionOptional = positionRepository.findById(id);
        Position position;
        if (!positionOptional.isPresent()) {
            throw new PositionNotFoundException("Position with id - " + id + " doesn't exist");
        } else {
            position = positionOptional.get();
            if (newPosition.getName() != null && !newPosition.getName().isEmpty())
                position.setName(newPosition.getName());
            if (newPosition.getDepartment() != null)
                position.setDepartment(newPosition.getDepartment());
        }
    }

    public void deletePosition(long id) {
        Optional<Position> position = positionRepository.findById(id);

        if (!position.isPresent())
            throw new PositionNotFoundException("Position with id - " + id + " doesn't exist");

        positionRepository.deleteById(id);
    }
}
