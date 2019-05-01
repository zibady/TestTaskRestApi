package net.zibady.task.kindgeek_test.controller;

import net.zibady.task.kindgeek_test.entity.Project;
import net.zibady.task.kindgeek_test.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> allPositions() {

        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable long id) {

        return projectService.getProject(id);
    }

    @PostMapping
    public void addProject(@RequestBody Project project) {

        projectService.addProject(project);
    }

    // оновити дані про посаду
    @PutMapping("/{id}")
    public void updateProject(@RequestBody Project project, @PathVariable long id) {
        projectService.updateProject(id, project);
    }
    // видалити працівника
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable long id) {
        projectService.deleteProject(id);
    }


}
