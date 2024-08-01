USE northwind;

-- Insert 10 new products into the new Sporting Goods Category
-- You can leave the Supplier Id empty for now, but you need to include
-- the product name, category, price, units in stock (20), units on order (0)
-- and re-order level (10) for each product.

INSERT INTO products
(
	product_name
    , category_id
    , unit_price
    , units_in_stock
    , units_on_order
    , reorder_level
)
VALUES ('Fishing pole', 9, 79.99, 20, 0, 10)
	, ('Basketball', 9, 39.99, 20, 0, 10)
    , ('Water bottle', 9, 9.99, 20, 0, 10)
    , ('Bear spray', 9, 13.99, 20, 0, 10)
    , ('Butane', 9, 12.99, 20, 0, 10)
    , ('Tent', 9, 49.99, 20, 0, 10)
    , ('Lighter', 9, 2.99, 20, 0, 10)
    , ('Down jacket', 9, 139.99, 20, 0, 10)
    , ('Pocket knife', 9, 299.99, 20, 0, 10)
    , ('Flashlight', 9, 5.99, 20, 0, 10)
;