package net.zibady.task.kindgeek_test.repository;


import net.zibady.task.kindgeek_test.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByName(String username);

}

