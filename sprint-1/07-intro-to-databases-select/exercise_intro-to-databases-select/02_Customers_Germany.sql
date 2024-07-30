USE northwind;

SELECT company_name
	, city
    , country
FROM customers
WHERE country = 'Germany';

-- The name, city and country of 
-- all customers in Germany
-- Expected: 11 rows

