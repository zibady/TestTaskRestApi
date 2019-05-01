package net.zibady.task.kindgeek_test.service;

import net.zibady.task.kindgeek_test.entity.Department;
import net.zibady.task.kindgeek_test.exception.DepartmentException;
import net.zibady.task.kindgeek_test.exception.DepartmentNotFoundException;
import net.zibady.task.kindgeek_test.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }


    public Department getDepartment(long id) {

        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with id - " + id + " doesn't exist"));
    }

    public void addDepartment(Department department) {

        if (department.getName() == null || department.getName().isEmpty())
            throw new DepartmentException("Department name can't be empty\n");

        departmentRepository.save(department);
    }

    public void updateDepartment(Department updatedDepartment) {

        long id = updatedDepartment.getId();
        Department department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department with id - " + id + " doesn't exist"));

        if (updatedDepartment.getName() != null && !updatedDepartment.getName().isEmpty()) {
            department.setName(updatedDepartment.getName());
        }
            departmentRepository.save(department);
    }

    public void deleteDepartment(long id) {
        try {
            departmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new DepartmentNotFoundException("Department with id - " + id + " doesn't exist");
        }
    }
}
