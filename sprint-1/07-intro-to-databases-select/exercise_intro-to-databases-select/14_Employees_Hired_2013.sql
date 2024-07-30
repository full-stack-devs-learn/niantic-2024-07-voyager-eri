USE northwind;

SELECT CONCAT(first_name, ' ', last_name) AS name
	, title
FROM employees
WHERE hire_date LIKE '2013%';

-- The employee name and title
-- of all employees who were hired
-- in 2013

-- Expected: 3 rows

