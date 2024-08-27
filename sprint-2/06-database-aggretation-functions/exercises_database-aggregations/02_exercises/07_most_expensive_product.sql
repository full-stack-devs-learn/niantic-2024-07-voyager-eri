-- 7. Select the price of the most expensive product

-- (1 row)

USE northwind;

SELECT MAX(sales_price)
FROM customer_orders;

