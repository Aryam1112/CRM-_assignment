INSERT INTO DEPARTMENT(id, name, budget) VALUES (1, 'Engineering', 1000000);
INSERT INTO DEPARTMENT(id, name, budget) VALUES (2, 'Sales', 500000);
INSERT INTO DEPARTMENT(id, name, budget) VALUES (3, 'People Ops', 300000);

INSERT INTO EMPLOYEE(id, name, email, department_id, date_of_joining, salary, manager_id)
VALUES (1, 'Alice', 'alice@example.com', 1, '2022-01-10', 120000, NULL);
INSERT INTO EMPLOYEE(id, name, email, department_id, date_of_joining, salary, manager_id)
VALUES (2, 'Bob', 'bob@example.com', 1, '2023-04-12', 90000, 1);
INSERT INTO EMPLOYEE(id, name, email, department_id, date_of_joining, salary, manager_id)
VALUES (3, 'Carol', 'carol@example.com', 2, '2021-06-01', 110000, NULL);

INSERT INTO PROJECT(id, name, start_date, end_date, department_id)
VALUES (1, 'Apollo', '2024-01-01', NULL, 1);
INSERT INTO PROJECT(id, name, start_date, end_date, department_id)
VALUES (2, 'Hermes', '2024-02-01', NULL, 1);
INSERT INTO PROJECT(id, name, start_date, end_date, department_id)
VALUES (3, 'RevenueMax', '2023-03-01', NULL, 2);

INSERT INTO EMPLOYEE_PROJECT(id, employee_id, project_id, assigned_date, role)
VALUES (1, 1, 1, '2024-01-10', 'Lead');
INSERT INTO EMPLOYEE_PROJECT(id, employee_id, project_id, assigned_date, role)
VALUES (2, 2, 2, '2024-02-15', 'Developer');
INSERT INTO EMPLOYEE_PROJECT(id, employee_id, project_id, assigned_date, role)
VALUES (3, 3, 3, '2024-02-20', 'AE');

INSERT INTO PERFORMANCE_REVIEW(id, employee_id, review_date, score, review_comments)
VALUES (1, 1, '2025-06-30', 9, 'Excellent delivery');
INSERT INTO PERFORMANCE_REVIEW(id, employee_id, review_date, score, review_comments)
VALUES (2, 1, '2025-03-31', 8, 'Great teamwork');
INSERT INTO PERFORMANCE_REVIEW(id, employee_id, review_date, score, review_comments)
VALUES (3, 1, '2024-12-31', 7, 'Solid year');
INSERT INTO PERFORMANCE_REVIEW(id, employee_id, review_date, score, review_comments)
VALUES (4, 2, '2025-06-30', 6, 'Needs improvement in testing');
INSERT INTO PERFORMANCE_REVIEW(id, employee_id, review_date, score, review_comments)
VALUES (5, 3, '2025-06-30', 8, 'Strong sales growth');
