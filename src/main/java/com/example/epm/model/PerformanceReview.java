package com.example.epm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class PerformanceReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull
    private LocalDate reviewDate;

    @NotNull
    private Integer score;

    @Column(length = 2000)
    private String reviewComments;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public LocalDate getReviewDate() { return reviewDate; }
    public void setReviewDate(LocalDate reviewDate) { this.reviewDate = reviewDate; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public String getReviewComments() { return reviewComments; }
    public void setReviewComments(String reviewComments) { this.reviewComments = reviewComments; }
}
