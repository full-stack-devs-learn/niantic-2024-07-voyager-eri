USE northwind;

-- Write a script that updates the price of all products 
-- within a category by a certain percent.
-- use variables to accept the Category name and the percent increase 

SET @category_name = 'Dairy Products'
	, @percent_increase = 0.25;
    
SELECT @category_id := category_id
FROM categories
WHERE category_name = @category_name;

SELECT @category_id;

UPDATE products
SET unit_price = unit_price * (1 + @percent_increase)
WHERE category_id = @category_id;