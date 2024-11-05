package by.payzov.it_academy.service;

import by.payzov.it_academy.entity.Employee;
import by.payzov.it_academy.entity.Project;
import by.payzov.it_academy.errors.ResourceAlreadyExistsException;
import by.payzov.it_academy.errors.ResourceNotFoundException;
import by.payzov.it_academy.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        Employee findedEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with id" + id + " not found"));
        return findedEmployee;
    }

    public Employee saveEmployee(Employee employee) {
        Optional<Employee> equalsProject =  employeeRepository.findByFullName(employee.getFirstName(),employee.getSecondName(),employee.getPatronymic());


        if (equalsProject.isPresent()) {
            throw new ResourceAlreadyExistsException("This Employee already exists");
        } else {
            Employee savedEmployee = (Optional.of(employeeRepository.save(employee)).orElseThrow(() -> new ResourceAlreadyExistsException("This Employee already exists")));
            return savedEmployee;
        }
    }

    public Employee removeEmployee(Long id) {
        Employee removedEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with id" + id + " not found"));
        employeeRepository.deleteById(id);
        return removedEmployee;
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee updatedEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with id" + id + " not found"));

        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setSecondName(employee.getSecondName());
        updatedEmployee.setPatronymic(employee.getPatronymic());
        updatedEmployee.setEmail(employee.getEmail());
        updatedEmployee.setPost(employee.getPost());

        employeeRepository.save(updatedEmployee);

        return updatedEmployee;
    }
}
