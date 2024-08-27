-- 13. Category popularity report

-- return a list of category names and the number of times
-- a film in that category has been rented

-- sort by most popular category first

-- (16 rows)

USE sakila;

SELECT c.name
	, COUNT(i.film_id) AS number_of_times_rented
FROM category AS c
INNER JOIN film_category AS fc
	ON c.category_id = fc.category_id
INNER JOIN inventory AS i
	ON fc.film_id = i.film_id
GROUP BY c.name
ORDER BY number_of_times_rented DESC
;

