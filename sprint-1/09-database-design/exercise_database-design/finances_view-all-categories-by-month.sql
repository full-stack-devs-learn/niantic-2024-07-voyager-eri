-- ALL CATEGORIES BY MONTH
USE finances;

SELECT category_id
    , SUM(amount)
FROM transactions
JOIN subcategories ON transactions.subcategory_id = subcategories.subcategory_id
WHERE MONTH(transaction_date) = 5
GROUP BY category_id;