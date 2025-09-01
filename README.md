# Employee Performance Management - Data Retrieval API

Tech: Spring Boot 3.3, Java 17, H2, JPA Specifications, Bean Validation, OpenAPI.

## Run

```bash
mvn spring-boot:run
```

H2 console: `/h2-console`  
OpenAPI/Swagger UI: `/swagger-ui/index.html`

## Key Endpoints

- `GET /api/employees`
  - Query params:
    - `reviewDate` = `YYYY-MM-DD`
    - `minScore`, `maxScore`
    - `departments` = comma-separated (supports multiple contains): e.g. `Engineering,Sales`
    - `projects` = comma-separated (supports multiple contains): e.g. `Apollo,Herm`
    - `page`, `size`, `sort` (default: name,asc)
- `GET /api/employees/{id}`
  - Returns employee info + department + projects + last 3 performance reviews

## Examples

```
GET /api/employees?reviewDate=2025-06-30&minScore=7&departments=Eng,Sales&projects=Ap
GET /api/employees/1
```

## Standards & Quality

- Layered architecture (Controller → Service → Repository)
- DTOs for API contracts
- Validation & global exception handling
- JPA Specifications for composable filters
- Checkstyle (google checks) + Maven Enforcer
- Simple integration test (`EmployeeControllerTest`)
```

