-- 19. List of all cities that have more than one customer.
-- Include City and total_customer_count.
-- Sort by total_customer_count desc
-- (use customers table)

-- (10 rows)

USE northwind;

SELECT city
	, COUNT(company_name) AS total_customer_count
FROM customers
GROUP BY city
HAVING COUNT(company_name) > 1
ORDER BY total_customer_count DESC



