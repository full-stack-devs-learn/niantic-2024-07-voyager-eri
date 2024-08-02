-- -----------------------
-- CREATING DATABASE
-- -----------------------

DROP DATABASE IF EXISTS finances;
CREATE DATABASE finances;
USE finances;

-- -----------------------
-- CREATING TABLES
-- -----------------------

CREATE TABLE IF NOT EXISTS transactions
(
	transaction_id INT NOT NULL AUTO_INCREMENT
    , transaction_date DATE
    , person_id INT
    , subcategory_id INT
    , vendor_id INT
    , amount INT NOT NULL
    , notes VARCHAR(4000) NULL
    , PRIMARY KEY (transaction_id)
);

CREATE TABLE IF NOT EXISTS categories
(
	category_id INT NOT NULL AUTO_INCREMENT
    , category_name VARCHAR(50)
    , description VARCHAR(4000) NULL
    , PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS subcategories
(
	subcategory_id INT NOT NULL AUTO_INCREMENT
    , subcategory_name VARCHAR(50)
    , category_id INT
    , description VARCHAR(4000) NULL
    , PRIMARY KEY (subcategory_id)
);

CREATE TABLE IF NOT EXISTS vendors
(
	vendor_id INT NOT NULL AUTO_INCREMENT
    , vendor_name VARCHAR(50)
    , PRIMARY KEY (vendor_id)
);

CREATE TABLE IF NOT EXISTS people
(
	person_id INT NOT NULL AUTO_INCREMENT
    , person_name VARCHAR(50)
    , PRIMARY KEY (person_id)
);

-- -------------------------------
-- INSERTING VALUES INTO PEOPLE
-- -------------------------------

INSERT INTO people (person_name)
VALUES ('Kirby')
	, ('Waddle Dee');

-- -------------------------------
-- INSERTING VALUES INTO VENDORS
-- -------------------------------

INSERT INTO vendors (vendor_name)
VALUES ('Landlord Inc.')
	, ('Shell Gas Station')
    , ('Fresh Mart')
    , ('CVS Pharmacy')
    , ('Cinemax Theater')
    , ('Delta Airlines')
    , ('PowerCo')
    , ('Metro Transit')
    , ('Joe''s Diner')
    , ('HealthFirst Clinic')
    , ('Netflix')
    , ('Oceanfront Resort')
    , ('WaterWorks')
    , ('AutoWorks')
    , ('Snack Haven')
    , ('Pharmax')
    , ('Sports Arena')
    , ('Zoom Car Rentals')
    , ('Farmer''s Market')
    , ('FastNet');

-- -----------------------------------
-- INSERTING VALUES INTO CATEGORIES
-- -----------------------------------

INSERT INTO categories (category_name)
VALUES ('Housing')
	, ('Transportation')
    , ('Food')
    , ('Healthcare')
    , ('Entertainment')
    , ('Vacation/Travel');

-- -----------------------------------
-- INSERTING VALUES INTO SUBCATEGORIES
-- -----------------------------------

INSERT INTO subcategories
(
	subcategory_name
    , category_id
)
VALUES ('Rent', 1)
	, ('Gas', 2)
    , ('Groceries', 3)
    , ('Rx', 4)
    , ('Movies', 5)
    , ('Flight', 6)
    , ('Utilities', 1)
    , ('Subway', 2)
    , ('Restaurant', 3)
    , ('Dr Visit', 4)
    , ('Netflix', 5)
    , ('Hotel', 6)
    , ('Car Maintenance', 2)
	, ('Snacks', 3)
    , ('OTC Medicine', 4)
    , ('Sports', 5)
    , ('Car Rental', 6)
    , ('Internet', 1);
    
-- -----------------------------------
-- INSERTING VALUES INTO TRANSACTIONS
-- -----------------------------------

INSERT INTO transactions
(
    transaction_date
    , person_id
    , subcategory_id
    , vendor_id
    , amount
    , notes
)
VALUES ('2024-05-01', 1, 1, 1, 1200, 'Dream Land rent payment')
	, ('2024-05-03', 2, 2, 2, 40, 'Filled up the Warp Star''s tank')
    , ('2024-05-05', 1, 3, 3, 150, 'Weekly Maxim Tomato shopping')
    , ('2024-05-07', 2, 4, 4, 20, 'Hammer upgrade prescription refill')
    , ('2024-05-10', 1, 5, 5, 30, 'Movie night out in Cappy Town')
    , ('2024-05-12', 2, 6, 6, 300, 'Booked a trip to the Fountain of Dreams')
    , ('2024-05-15', 1, 7, 7, 100, 'Electricity bill payment for the Kirby Cafe')
    , ('2024-05-17', 2, 8, 8, 20, 'Monthly Warp Star pass')
    , ('2024-05-20', 1, 9, 9, 25, 'Dinner with King Dedede')
    , ('2024-05-22', 2, 10, 10, 50, 'Routine check-up with Meta Knight')
    , ('2024-05-25', 1, 11, 11, 15, 'Monthly subscription fee to the Kirby Fan Club')
    , ('2024-05-28', 2, 12, 12, 200, 'Hotel booking in Dream Land')
    , ('2024-06-01', 2, 7, 13, 50, 'Water bill payment for the Fountain of Dreams')
    , ('2024-06-04', 1, 13, 14, 100, 'Warp Star oil change')
    , ('2024-06-09', 2, 14, 15, 10, 'Snacks for a movie night in Cappy Town')
	, ('2024-06-14', 1, 15, 16, 15, 'Cold medicine for the Waddle Dees')
    , ('2024-06-18', 2, 16, 17, 50, 'Tickets to a Hammer Swing competition')
    , ('2024-06-23', 1, 17, 18, 150, 'Rental Warp Star for a trip')
    , ('2024-06-26', 2, 3, 19, 80, 'Fresh Maxim Tomatoes and groceries')
    , ('2024-06-30', 1, 18, 20, 60, 'Internet service provider bill for the Kirby Cafe');
    
-- -----------------------------------
-- INSERTING FOREIGN KEYS
-- -----------------------------------

ALTER TABLE transactions
ADD CONSTRAINT fk_transactions_subcategories
FOREIGN KEY (subcategory_id) REFERENCES subcategories(subcategory_id);

ALTER TABLE transactions
ADD CONSTRAINT fk_transactions_vendors
FOREIGN KEY (vendor_id) REFERENCES vendors(vendor_id);

ALTER TABLE transactions
ADD CONSTRAINT fk_transactions_people
FOREIGN KEY (person_id) REFERENCES people(person_id);

ALTER TABLE subcategories
ADD CONSTRAINT fk_subcategories_categories
FOREIGN KEY (category_id) REFERENCES categories(category_id);