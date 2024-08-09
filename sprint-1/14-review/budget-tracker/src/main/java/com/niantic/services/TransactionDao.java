package com.niantic.services;

import com.niantic.models.Transaction;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class TransactionDao
{
    private final JdbcTemplate jdbcTemplate;

    public TransactionDao()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/budget");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addTransaction(Transaction transaction)
    {
        String sql = """
                INSERT INTO transactions
                (user_id
                , sub_category_id
                , vendor_id
                , transaction_date
                , amount
                , notes)
                VALUES
                (?, ?, ?, ?, ?, ?);
                """;

        jdbcTemplate.update(sql
                , transaction.getUserId()
                , transaction.getSubCategoryId()
                , transaction.getVendorId()
                , transaction.getDate()
                , transaction.getAmount()
                , transaction.getNotes());
    }

    public ArrayList<Transaction> getTransactionByUser(int userId)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();

        String sql = """
                SELECT transaction_id
                    , user_id
                    , sub_category_id
                    , vendor_id
                    , transaction_date
                    , amount
                    , notes
                FROM transactions
                WHERE user_id = ?;
                """;

        var row = jdbcTemplate.queryForRowSet(sql, userId);

        while(row.next())
        {
            int transactionId = row.getInt("transaction_id");
            int subCategoryId = row.getInt("sub_category_id");
            int vendorId = row.getInt("vendor_id");

            LocalDate transactionDate = null;
            Date date = row.getDate("transaction_date");
            if(date != null)
            {
                transactionDate = date.toLocalDate();
            }

            BigDecimal amount = row.getBigDecimal("amount");
            String notes = row.getString("notes");

            var transaction = new Transaction(transactionId, userId, subCategoryId, vendorId, transactionDate, amount, notes);
            transactions.add(transaction);
        }

        return transactions;
    }
}
