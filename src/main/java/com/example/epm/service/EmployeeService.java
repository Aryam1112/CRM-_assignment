package com.example.epm.service;

import com.example.epm.dto.*;
import com.example.epm.model.*;
import com.example.epm.repo.*;
import com.example.epm.spec.EmployeeSpecifications;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PerformanceReviewRepository performanceReviewRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           PerformanceReviewRepository performanceReviewRepository) {
        this.employeeRepository = employeeRepository;
        this.performanceReviewRepository = performanceReviewRepository;
    }

    public Page<EmployeeSummaryDto> searchEmployees(
            LocalDate reviewDate, Integer minScore, Integer maxScore,
            List<String> departmentTerms, List<String> projectTerms,
            Pageable pageable) {

        Specification<Employee> spec = Specification.where(null)
                .and(EmployeeSpecifications.scoreOnReviewDate(reviewDate, minScore, maxScore))
                .and(EmployeeSpecifications.departmentNameContainsAny(departmentTerms))
                .and(EmployeeSpecifications.projectNameContainsAny(projectTerms));

        return employeeRepository.findAll(spec, pageable)
                .map(e -> new EmployeeSummaryDto(
                        e.getId(),
                        e.getName(),
                        e.getEmail(),
                        e.getDepartment() != null ? e.getDepartment().getName() : null));
    }

    public EmployeeDetailDto getEmployeeDetail(Long id) {
        Employee e = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found id=" + id));

        List<String> projectNames = e.getEmployeeProjects().stream()
                .map(ep -> ep.getProject().getName())
                .distinct()
                .collect(Collectors.toList());

        List<PerformanceReviewDto> lastThree = performanceReviewRepository.findTop3ByEmployeeOrderByReviewDateDesc(e)
                .stream()
                .map(pr -> new PerformanceReviewDto(pr.getId(), pr.getReviewDate(), pr.getScore(), pr.getReviewComments()))
                .collect(Collectors.toList());

        return new EmployeeDetailDto(
                e.getId(), e.getName(), e.getEmail(),
                e.getDepartment() != null ? e.getDepartment().getName() : null,
                e.getDateOfJoining(), e.getSalary(),
                e.getManager() != null ? e.getManager().getName() : null,
                projectNames, lastThree);
    }
}
