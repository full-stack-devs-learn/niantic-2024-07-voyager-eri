USE finances;

SET @name = 'Kirby';
SET @transaction_date = '2024-07-02';
SET @subcategory_name = 'Restaurant';
SET @vendor_name = 'Joe''s Diner';
SET @amount = 12;
SET @notes = 'Boba drink with friends';

SELECT @person_id := person_id
FROM people
WHERE person_name = @person_name;

SELECT @subcategory_id := subcategory_id
FROM subcategories
WHERE subcategory_name = @subcategory_name;

SELECT @vendor_id := vendor_id
FROM vendors
WHERE vendor_name = @vendor_name;

INSERT INTO transactions
(
    transaction_date
    , person_id
    , subcategory_id
    , vendor_id
    , amount
    , notes
)
VALUES
(
	@transaction_date
    , @person_id
    , @subcategory_id
    , @vendor_id
    , @amount
    , @notes
);

SELECT *
FROM transactions
WHERE transaction_date = @transaction_date;