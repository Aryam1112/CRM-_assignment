package com.example.epm.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record EmployeeDetailDto(
        Long id,
        String name,
        String email,
        String departmentName,
        LocalDate dateOfJoining,
        BigDecimal salary,
        String managerName,
        List<String> projectNames,
        List<PerformanceReviewDto> lastThreeReviews
) { }
