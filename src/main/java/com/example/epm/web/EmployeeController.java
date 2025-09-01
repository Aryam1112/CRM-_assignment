package com.example.epm.web;

import com.example.epm.dto.EmployeeDetailDto;
import com.example.epm.dto.EmployeeSummaryDto;
import com.example.epm.service.EmployeeService;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * GET /api/employees
     * Filters:
     *  - reviewDate (YYYY-MM-DD), minScore, maxScore (optional)
     *  - departments: comma-separated list; name contains (case-insensitive)
     *  - projects: comma-separated list; name contains (case-insensitive)
     * Pagination:
     *  - page (0-based), size, sort (e.g., name,asc)
     */
    @GetMapping
    public Page<EmployeeSummaryDto> search(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reviewDate,
            @RequestParam(required = false) Integer minScore,
            @RequestParam(required = false) Integer maxScore,
            @RequestParam(required = false) List<String> departments,
            @RequestParam(required = false) List<String> projects,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) int size,
            @RequestParam(required = false) String sort
    ) {
        Pageable pageable = PageRequest.of(page, size,
                sort != null ? Sort.by(sort.split(",")[0]).ascending() : Sort.by("name").ascending());
        return employeeService.searchEmployees(
                reviewDate, minScore, maxScore, departments, projects, pageable);
    }

    /** GET /api/employees/{id} - detailed view */
    @GetMapping("/{id}")
    public EmployeeDetailDto detail(@PathVariable Long id) {
        return employeeService.getEmployeeDetail(id);
    }
}
