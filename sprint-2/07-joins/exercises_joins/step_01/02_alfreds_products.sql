-- 2. The name of all products that
-- have been ordered by Alfreds Futterkiste
-- Include each product only once
-- (11 rows)

USE northwind;

SELECT DISTINCT p.product_name
FROM customers AS c
INNER JOIN orders AS o
	ON c.customer_id = o.customer_id
INNER JOIN order_details AS od
	ON o.order_id = od.order_id
INNER JOIN products AS p
	ON od.product_id = p.product_id
WHERE c.company_name = "Alfreds Futterkiste";