package net.zibady.task.kindgeek_test.controller;

import net.zibady.task.kindgeek_test.entity.Department;
import net.zibady.task.kindgeek_test.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> allDepartment () {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartment (@PathVariable long id) {
        return departmentService.getDepartment(id);
    }

    @PostMapping
    public void addDepartment (@RequestBody Department department) {
        departmentService.addDepartment(department);
    }

    @PutMapping
    public void updateDepartment (@RequestBody Department department, @PathVariable long id) {
        departmentService.updateDepartment(department, id);
    }

    @DeleteMapping
    public void deleteDepartment (@PathVariable long id) {
        departmentService.deleteDepartment(id);
    }

}
