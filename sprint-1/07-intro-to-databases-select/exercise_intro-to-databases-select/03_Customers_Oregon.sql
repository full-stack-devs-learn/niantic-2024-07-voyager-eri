use northwind;

SELECT company_name
	, city
    , region
FROM customers
WHERE region = 'OR';

-- The name, city and state of 
-- all customers in Oregon (OR)
-- Expected: 4 rows


