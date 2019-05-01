package net.zibady.task.kindgeek_test.repository;

import net.zibady.task.kindgeek_test.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
}
