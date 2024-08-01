USE northwind;

-- Create a full script with variables that allows you 
-- to add a new Customer into the database. Then create an order for them
-- that includes 5 products. Create 5 variables at the top of the script
-- that store the 5 product names the customer wants to buy. You will use
-- the product names to find the id

-- Requirements
/*
    I should be able to run the script on my machine without having to
    write any queries to look up the id's of any tables. (The only id that
    I should have to know is the new CustomerId - which is a 5 letter code 
    that we will choose )

    Use variables at the top of the script to collect User Input. The rest 
    of the script should run without hardcoding ANY values.

    I.E. if I want to add a new customer, and order, I should be able 
    to change the variables at the beginning of the script, and run the whole
    script.
*/

/*
Customer must include: 
    Customer Id: 5 letter code
    Company Name: (You can use chatgpt or https://businessnamegenerator.com to pick a name)
    Contact Name: (you can use chatgpt or http://random-name-generator.info to pick a name)
    Address
    City
    Region
    Postal Code
    Country

Order:
    Order id: auto generated
    Customer Id: id from the inserted customer
    Order Date: today's date
    Ship Name: Contact name
    Ship Address: The company address information

OrderDetails: (Create 5 line items)
    Order Id: the one created above LAST INSERT ID @ ORDERID,
    Product Id: use the product names to select/find the id of each product
    Unit Price: use the default list price of each product
    Quantity: you decide - between 1-10
    Discount: 0
*/

-- ------------------------
-- SETTING ALL VARIABLES
-- ------------------------

SET @product_1 = 'Scottish Longbreads';
SET @product_2 = 'Gudbrandsdalsost';
SET @product_3 = 'Outback Lager';
SET @product_4 = 'Flotemysost';
SET @product_5 = 'Mozzarella di Giovanni';

SET @customer_id = 'CAGFA'
	, @company_name = 'Cat Girl Factory'
    , @contact_name = 'Chairman Meow'
    , @address = '123 Cat Girl Ave.'
    , @city = 'Seattle'
    , @region = 'Washington'
    , @postal_code = '98104'
    , @country = 'USA';
    
-- ------------------------
-- GENERATING A CUSTOMER
-- ------------------------

-- INSERT INTO customers
-- (
-- 	customer_id
--     , company_name
--     , contact_name
--     , address
--     , city
--     , region
--     , postal_code
--     , country
-- )
-- VALUES
-- (
-- 	@customer_id
--     , @company_name
--     , @contact_name
--     , @address
--     , @city
--     , @region
--     , @postal_code
--     , @country
-- );

-- ------------------------
-- GENERATING ORDER
-- ------------------------

INSERT INTO orders
(
	customer_id
    , order_date
    , ship_name
    , ship_address
)
VALUES
(
	@customer_id
    , NOW()
    , @contact_name
    , @address
);

-- ------------------------
-- GENERATING ORDER DETAILS
-- ------------------------

-- PRODUCT 1
-- ------------------------
SET @order_id = LAST_INSERT_ID();

SELECT @product_id_1 = product_id
	, @product_price_1 := unit_price
FROM products
WHERE product_name = @product_1;

INSERT INTO order_details (order_id, product_id, unit_price)
VALUES (@order_id, @product_id_1, @product_price_1);

-- PRODUCT 2
-- ------------------------
SELECT @product_id_2 = product_id
	, @product_price_2 := unit_price
FROM products
WHERE product_name = @product_2;

INSERT INTO order_details (order_id, product_id, unit_price)
VALUES (@order_id, @product_id_2, @product_price_2);
	
-- PRODUCT 3
-- ------------------------
SELECT @product_id_3 = product_id
	, @product_price_3 := unit_price
FROM products
WHERE product_name = @product_3;

INSERT INTO order_details (order_id, product_id, unit_price)
VALUES (@order_id, @product_id_3, @product_price_3);

-- PRODUCT 4
-- ------------------------
SELECT @product_id_4 = product_id
	, @product_price_4 := unit_price
FROM products
WHERE product_name = @product_4;

INSERT INTO order_details (order_id, product_id, unit_price)
VALUES (@order_id, @product_id_4, @product_price_4);

-- PRODUCT 5
-- ------------------------
SELECT @product_id_5 = product_id
	, @product_price_5 := unit_price
FROM products
WHERE product_name = @product_5;

INSERT INTO order_details (order_id, product_id, unit_price)
VALUES (@order_id, @product_id_5, @product_price_5);