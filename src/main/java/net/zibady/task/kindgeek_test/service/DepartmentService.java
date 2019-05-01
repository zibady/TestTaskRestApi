package net.zibady.task.kindgeek_test.service;

import net.zibady.task.kindgeek_test.entity.Department;
import net.zibady.task.kindgeek_test.exception.DepartmentException;
import net.zibady.task.kindgeek_test.exception.DepartmentNotFoundException;
import net.zibady.task.kindgeek_test.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public void addDepartment(Department department) {

        if (department.getName() == null || department.getName().isEmpty())
            throw new DepartmentException("Department name can't be empty\n");

        departmentRepository.save(department);
    }

    public Department getDepartment(long id) {
        Optional<Department> department = departmentRepository.findById(id);

        if (!department.isPresent())
            throw new DepartmentNotFoundException("Department with id - " + id + " doesn't exist");

        return department.get();
    }

    public void updateDepartment(Department newDepartment, long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        Department department;

        if (!departmentOptional.isPresent()) {
            throw new DepartmentNotFoundException("Department with id - " + id + " doesn't exist");
        } else {
            department = departmentOptional.get();

            if (newDepartment.getName() != null && !newDepartment.getName().isEmpty())
                department.setName(newDepartment.getName());

            departmentRepository.save(department);
        }
    }

    public void deleteDepartment(long id) {
        Optional<Department> department = departmentRepository.findById(id);

        if (!department.isPresent())
            throw new DepartmentNotFoundException("Department with id - " + id + " doesn't exist");

         departmentRepository.deleteById(id);
    }
}
