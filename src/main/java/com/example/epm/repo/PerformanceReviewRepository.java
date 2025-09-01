package com.example.epm.repo;

import com.example.epm.model.PerformanceReview;
import com.example.epm.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long> {
    List<PerformanceReview> findTop3ByEmployeeOrderByReviewDateDesc(Employee employee);
}
