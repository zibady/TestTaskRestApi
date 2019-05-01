package net.zibady.task.kindgeek_test.service;

import net.zibady.task.kindgeek_test.entity.Department;
import net.zibady.task.kindgeek_test.entity.Position;
import net.zibady.task.kindgeek_test.exception.PositionException;
import net.zibady.task.kindgeek_test.exception.PositionNotFoundException;
import net.zibady.task.kindgeek_test.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return positionRepository.findById(id)
                .orElseThrow(() -> new PositionNotFoundException("Position with id : " + id + " doesn't exist"));
    }

    public void addPosition(Position position) {

        if (position.getName() == null || position.getName().isEmpty())
            throw new PositionException("Position name can't be empty\n");

        if (position.getDepartment() == null )
            throw new PositionException("Position should belong to department. Department name can't be empty\n");

        Department department = departmentService.getDepartment(position.getDepartment().getId());

        position.setDepartment(department);

        positionRepository.save(position);

    }

    public void updatePosition(Position updatedPosition) {

        long id = updatedPosition.getId();
        Position position = positionRepository.findById(id)
                            .orElseThrow(() -> new  PositionNotFoundException("Position with id : " + id + " doesn't exist"));

            if (updatedPosition.getName() != null && !updatedPosition.getName().isEmpty())
                position.setName(updatedPosition.getName());

            if (updatedPosition.getDepartment() != null) {
                Department department = departmentService.getDepartment(updatedPosition.getDepartment().getId());
                position.setDepartment(department);
            }

        positionRepository.save(position);
    }

    public void deletePosition(long id) {
        try {
            positionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new PositionNotFoundException("Position with id : " + id + " doesn't exist");
        }
    }
}
