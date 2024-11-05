package by.payzov.it_academy.repository;

import by.payzov.it_academy.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    Optional<Project> findByName(String name);

    @Query("SELECT p.name, p.description, CONCAT(e.secondName, ' ', e.firstName, ' ', e.patronymic) FROM Project p JOIN p.employees e")
    List<Object[]> findAllProjectsWithEmployeeNames();
}
