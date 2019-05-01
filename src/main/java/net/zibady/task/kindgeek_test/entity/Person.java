package net.zibady.task.kindgeek_test.entity;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "position_id")
   // @JsonManagedReference
    private Position position;

    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "department_id")
   // @JsonManagedReference
    private Department department;

//    @ManyToOne(targetEntity = Project.class)
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "project_id")
  //  @JsonManagedReference
    private Project project;

    public Person() {
    }

    public Person(String name, Position position, Project project) {
        this.name = name;
        this.position = position;
        this.project = project;
    }

    public Person(String name, Position position, Department department, Project project) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
