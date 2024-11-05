package by.payzov.it_academy.repository;

import by.payzov.it_academy.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.firstName = :firstName AND e.secondName = :secondName AND e.patronymic = :patronymic")
    Optional<Employee> findByFullName(@Param("firstName") String firstName, @Param("secondName") String secondName, @Param("patronymic") String patronymic);

}
