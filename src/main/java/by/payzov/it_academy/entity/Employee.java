package by.payzov.it_academy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "firstName", length = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Only Latin letters")
    private String firstName;
    @Column(name = "secondName", length = 40)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Only Latin letters")
    private String secondName;
    @Column(name = "patronymic", length = 40)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Only Latin letters")
    private String patronymic;
    @Column(name = "email", length = 50)
    @Email(message = "Email should be valid")
    private String email;
    @Column(name = "post", length = 40)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Only Latin letters")
    private String post;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "participates_projects ",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects = new HashSet<>();

    public Employee(Long id, String firstName, String secondName, String patronymic, String email, String post, Set<Project> projects) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.email = email;
        this.post = post;
        this.projects = projects;
    }

    public Employee(String firstName, String secondName, String patronymic, String email, String post, Set<Project> projects) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.email = email;
        this.post = post;
        this.projects = projects;
    }

    public Employee(String firstName, String secondName, String patronymic, String email, String post) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.email = email;
        this.post = post;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void assignProject(Project project){
        projects.add(project);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                ", post='" + post + '\'' +
                ", projects=" + projects +
                '}';
    }
}
