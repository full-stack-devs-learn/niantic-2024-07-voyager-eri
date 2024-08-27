-- 6. Select the full name of each employee
-- and the full name of their manager

-- hint look at the employee table to see how to
-- determine who the manager is of each employee

-- you will need to do a "self join" to the employees table
-- and you will need to create table aliases to complete this query

-- (9 rows)

USE northwind;

SELECT CONCAT(t1.first_name, " ", t1.last_name) AS employee_full_name
	, CONCAT(t2.first_name, " ", t2.last_name) AS manager_full_name
FROM employees t1
LEFT JOIN employees t2
	ON t1.reports_to = t2.employee_id;