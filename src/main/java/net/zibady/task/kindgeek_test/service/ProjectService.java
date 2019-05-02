package net.zibady.task.kindgeek_test.service;

import net.zibady.task.kindgeek_test.entity.Project;
import net.zibady.task.kindgeek_test.exception.ProjectException;
import net.zibady.task.kindgeek_test.exception.ProjectNotFoundException;
import net.zibady.task.kindgeek_test.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id : " + id + " doesn't exist"));
    }

    public void addProject(Project project) {

        if (project.getName() == null || project.getName().isEmpty())
            throw new ProjectException("Project name can't be empty\n");

        projectRepository.save(project);
    }

    public void updateProject(Project updatedProject) {

        long id = updatedProject.getId();
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id : " + id + " doesn't exist"));

        if (updatedProject.getName() != null && !updatedProject.getName().isEmpty())
            project.setName(updatedProject.getName());
        if (updatedProject.getDescription() != null && !updatedProject.getDescription().isEmpty())
            project.setDescription(updatedProject.getDescription());

        projectRepository.save(project);
    }

    public void deleteProject(long id) {
        try {
            projectRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ProjectNotFoundException("Project with id : " + id + " doesn't exist");
        } catch (DataIntegrityViolationException ex) {
            throw new ProjectException("Current Project has assigned people. Please, unassign or delete them to delete the Project");
        }
    }
}
