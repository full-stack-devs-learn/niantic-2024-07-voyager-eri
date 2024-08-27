-- 9. Most Popular Film Categories
-- Return the total number of actors
-- who have acted in a category
-- sort most popular category to least

-- Columns: category_name, actor_count
--
-- (16 rows)

USE sakila;

SELECT c.name
	, COUNT(DISTINCT(fa.actor_id)) AS number_of_actors
FROM category AS c
INNER JOIN film_category AS fc
	ON c.category_id = fc.category_id
INNER JOIN film_actor AS fa
	ON fc.film_id = fa.film_id
GROUP BY c.category_id
ORDER BY number_of_actors DESC
;