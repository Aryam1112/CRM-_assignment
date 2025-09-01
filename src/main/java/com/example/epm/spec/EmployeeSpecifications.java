package com.example.epm.spec;

import com.example.epm.model.*;
import jakarta.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecifications {

    public static Specification<Employee> departmentNameContainsAny(List<String> terms) {
        return (root, query, cb) -> {
            if (terms == null || terms.isEmpty()) return cb.conjunction();
            Join<Employee, Department> dept = root.join("department", JoinType.LEFT);
            var predicates = terms.stream()
                .map(t -> cb.like(cb.lower(dept.get("name")), "%" + t.toLowerCase() + "%"))
                .toArray(javax.persistence.criteria.Predicate[]::new);
            return cb.or(predicates);
        };
    }

    public static Specification<Employee> projectNameContainsAny(List<String> terms) {
        return (root, query, cb) -> {
            if (terms == null || terms.isEmpty()) return cb.conjunction();
            query.distinct(true);
            Join<Employee, EmployeeProject> ep = root.join("employeeProjects", JoinType.LEFT);
            Join<EmployeeProject, Project> project = ep.join("project", JoinType.LEFT);
            var predicates = terms.stream()
                .map(t -> cb.like(cb.lower(project.get("name")), "%" + t.toLowerCase() + "%"))
                .toArray(javax.persistence.criteria.Predicate[]::new);
            return cb.or(predicates);
        };
    }

    public static Specification<Employee> scoreOnReviewDate(LocalDate reviewDate, Integer minScore, Integer maxScore) {
        return (root, query, cb) -> {
            if (reviewDate == null) return cb.conjunction();
            Subquery<Long> sub = query.subquery(Long.class);
            Root<PerformanceReview> pr = sub.from(PerformanceReview.class);
            sub.select(pr.get("employee").get("id"));
            Predicate p = cb.equal(pr.get("reviewDate"), reviewDate);
            if (minScore != null) { p = cb.and(p, cb.greaterThanOrEqualTo(pr.get("score"), minScore)); }
            if (maxScore != null) { p = cb.and(p, cb.lessThanOrEqualTo(pr.get("score"), maxScore)); }
            sub.where(cb.and(p));
            return root.get("id").in(sub);
        };
    }
}
