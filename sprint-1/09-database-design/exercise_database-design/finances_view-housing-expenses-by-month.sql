-- HOUSING EXPENSES BY MONTH

USE finances;

SELECT transactions.subcategory_id
	, sum(amount)
FROM transactions
JOIN subcategories ON transactions.subcategory_id = subcategories.subcategory_id
WHERE MONTH(transaction_date) = 5 AND category_id = 1
GROUP BY transactions.subcategory_id;