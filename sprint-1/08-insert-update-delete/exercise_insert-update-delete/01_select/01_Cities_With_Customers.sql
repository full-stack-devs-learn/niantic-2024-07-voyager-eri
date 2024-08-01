USE northwind;

-- Return all countries and cities where we 
-- have customers

SELECT DISTINCT country
	, city
FROM customers;


-- Expected: 69 Rows

