package net.zibady.task.kindgeek_test.repository;


import net.zibady.task.kindgeek_test.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByName(String name);
}

