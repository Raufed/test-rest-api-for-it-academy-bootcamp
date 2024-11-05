package by.payzov.it_academy.controller;

import by.payzov.it_academy.entity.Project;
import by.payzov.it_academy.service.ProjectService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    private ProjectService projectService;
    @GetMapping
    public List<Project> projects() {
        logger.info("Retrieving all projects");
        return projectService.findAllprojects();
    }
    @GetMapping("/with-employees")
    public List<String> getProjectsWithEmployeeNames() {
        logger.info("Retrieving all projects with employees");
        return projectService.getAllProjectsWithEmployeeNames();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Project> projects(@PathVariable("id") Long id) {
        logger.info("Retrieving project with ID: {}", id);
        return ResponseEntity.ok(projectService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Project> addProject(@Valid @RequestBody Project project) {
        logger.info("Adding a new project: {}", project.getName());
        return ResponseEntity.ok(projectService.saveProject(project));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Project> removeProject(@PathVariable("id") Long id) {
        logger.info("Removing project with ID: {}", id);
        return ResponseEntity.ok(projectService.removeProject(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Long id, @RequestBody Project project) {
        logger.info("Updating project with ID: {}", id);
        return ResponseEntity.ok(projectService.updateProject(id, project));
    }
    @PutMapping("/{project_id}/employees/{employee_id}")
    public ResponseEntity<Project> addEmployeeToProject(@PathVariable("project_id") Long project_id, @PathVariable("employee_id") Long employee_id) {
        logger.info("Adding employee with ID {} to project with ID {}", employee_id, project_id);
        return ResponseEntity.ok(projectService.addEmployeeToProject(project_id, employee_id));
    }
}
