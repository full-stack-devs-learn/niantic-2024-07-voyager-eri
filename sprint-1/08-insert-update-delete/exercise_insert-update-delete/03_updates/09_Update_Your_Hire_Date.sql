USE northwind;

-- write an update statement and change your hire date to 
-- Monday 2 weeks ago

UPDATE employees
SET hire_date = DATE_SUB(CURDATE(), INTERVAL 2 WEEK) - INTERVAL WEEKDAY(CURDATE()) DAY
WHERE last_name = 'Ishikawa';