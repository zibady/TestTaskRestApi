package net.zibady.task.kindgeek_test.service;

import net.zibady.task.kindgeek_test.entity.Project;
import net.zibady.task.kindgeek_test.exception.ProjectException;
import net.zibady.task.kindgeek_test.exception.ProjectNotFoundException;
import net.zibady.task.kindgeek_test.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {

        return projectRepository.findAll();
    }

    public Project getProject(long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (!project.isPresent())
            throw new ProjectNotFoundException("Project with id - " + id + " doesn't exist");

        return project.get();
    }

    public void addProject(Project project) {

        if (project.getName() == null || project.getName().isEmpty())
            throw new ProjectException("Project name can't be empty\n");

        projectRepository.save(project);
    }

    public void updateProject(long id, Project newProject) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        Project project;

        if (!projectOptional.isPresent())
            throw new ProjectNotFoundException("Project with id - " + id + " doesn't exist");

        project = projectOptional.get();

        if (newProject.getName() != null && !newProject.getName().isEmpty())
            project.setName(newProject.getName());
        if (newProject.getDescription() != null && !newProject.getDescription().isEmpty())
            project.setDescription(newProject.getDescription());

        projectRepository.save(project);
    }

    public void deleteProject(long id) {
        Optional<Project> project = projectRepository.findById(id);

        if (!project.isPresent())
            throw new ProjectNotFoundException("Project with id - " + id + " doesn't exist");

        projectRepository.deleteById(id);
    }
}
