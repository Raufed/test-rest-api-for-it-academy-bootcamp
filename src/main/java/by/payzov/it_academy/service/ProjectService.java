package by.payzov.it_academy.service;

import by.payzov.it_academy.entity.Employee;
import by.payzov.it_academy.entity.Project;
import by.payzov.it_academy.errors.ResourceAlreadyExistsException;
import by.payzov.it_academy.errors.ResourceNotFoundException;
import by.payzov.it_academy.repository.EmployeeRepository;
import by.payzov.it_academy.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Project> findAllprojects() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        Project findedProject = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project with id " + id + " not found"));
        return findedProject;
    }
    public Project removeProject(Long id) {
        Project removedProject = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project with id " + id + " not found"));
        projectRepository.deleteById(id);
        return removedProject;
    }
    public Project saveProject(Project project) {
        Optional<Project> equalsProject =  projectRepository.findByName(project.getName());


        if (equalsProject.isPresent()) {
            throw new ResourceAlreadyExistsException("This project already exists");
        } else {
            Project savedProject = (Optional.of(projectRepository.save(project)).orElseThrow(() -> new ResourceAlreadyExistsException("This project already exists")));
            return savedProject;
        }

    }

    public Project updateProject(Long id, Project project) {
        Project updatedProject = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project with id " + id + " not found"));
        updatedProject.setName(project.getName());
        updatedProject.setDescription(project.getDescription());
        projectRepository.save(updatedProject);

        return updatedProject;
    }

    public Project addEmployeeToProject(Long projectId, Long employeeId) {
        Project findedProject = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project with id " + projectId + " not found"));
        Employee findedEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee with id " + employeeId + " not found"));

        findedEmployee.assignProject(findedProject);
        employeeRepository.save(findedEmployee);

        return findedProject;
    }

    public List<String> getAllProjectsWithEmployeeNames() {
        List<Object[]> results = projectRepository.findAllProjectsWithEmployeeNames();
        List<String> projectDetails = new ArrayList<>();

        for (Object[] row : results) {
            String projectName = (String) row[0];
            String projectDescription = (String) row[1];
            String employeeFullName = (String) row[2];

            projectDetails.add("Проект: " + projectName + ", Описание: " + projectDescription +
                    ", Сотрудник: " + employeeFullName);
        }

        return projectDetails;
    }
}
