package com.example.epm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    private Double budget;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "department")
    private Set<Project> projects;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }
}
