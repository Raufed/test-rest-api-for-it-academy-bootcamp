package by.payzov.it_academy.controller;

import by.payzov.it_academy.entity.Employee;
import by.payzov.it_academy.entity.Project;
import by.payzov.it_academy.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeesController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeesController.class);
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public List<Employee> getEmployees() {
        logger.info("Retrieving all employees");
        return employeeService.findAllEmployees();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
        logger.info("Retrieving employee with ID: {}", id);
        return ResponseEntity.ok(employeeService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        logger.info("Adding a new employee: {}", employee.getFirstName() + " " + employee.getSecondName() + " " + employee.getPatronymic());
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> removeEmployee(@PathVariable("id") Long id) {
        logger.info("Removing employee with ID: {}", id);
        return ResponseEntity.ok(employeeService.removeEmployee(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateProjectEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        logger.info("Updating employee with ID: {}", id);
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

}
