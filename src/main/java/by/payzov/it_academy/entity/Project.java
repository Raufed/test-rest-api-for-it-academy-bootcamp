package by.payzov.it_academy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

@Entity
@Table
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 60, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Only Latin letters are allowed")
    private String name;
    @Column(name = "description", length = 150)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Only Latin letters are allowed")
    private String description;
    //@JsonIgnore
    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    private Set<Employee> employees;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Project(Long id, String name, String description, Set<Employee> employees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    public Project(String name, String description, Set<Employee> employees) {
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Project))
            return false;
        Project other = (Project) o;
        boolean currencyCodeEquals = (this.description == null && other.description == null)
                || (this.description != null && this.description.equals(other.description));
        return this.name == other.name && currencyCodeEquals;
    }

    @Override
    public final int hashCode() {
        int result = 511;
        if (name != null) {
            result = 31 * result + name.hashCode();
        }
        if (description != null) {
            result = 31 * result + description.hashCode();
        }
        return result;
    }

}
