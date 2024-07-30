USE northwind;

SELECT CONCAT(first_name, ' ', last_name) AS name
    , title
FROM employees
WHERE reports_to IS NULL;

-- The employee name and title
-- of all employees who do not
-- have a boss

-- Expected: 1 row

