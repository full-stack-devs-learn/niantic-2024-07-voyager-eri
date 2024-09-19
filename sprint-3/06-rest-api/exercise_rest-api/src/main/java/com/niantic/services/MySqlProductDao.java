package com.niantic.services;

import com.niantic.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MySqlProductDao implements ProductDao
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlProductDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Product> getProductsByCategory(int catId)
    {
        List<Product> products = new ArrayList<>();

        String sql = """
                    SELECT product_id
                        , category_id
                        , product_name
                        , quantity_per_unit
                        , unit_price
                        , units_in_stock
                        , units_on_order
                        , reorder_level
                    FROM products
                    WHERE category_id = ?
                    """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, catId);

        while(row.next())
        {
            int productId = row.getInt("product_id");
            String productName = row.getString("product_name");
            String quantityPerUnit = row.getString("quantity_per_unit");
            double unitPrice = row.getDouble("unit_price");
            int unitsInStock = row.getInt("units_in_stock");
            int unitsOnOrder = row.getInt("units_on_order");
            int reorderLevel = row.getInt("reorder_level");

            Product product = new Product(productId, catId, productName, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getProductById(int productId)
    {
        String sql = """
                    SELECT product_id
                        , category_id
                        , product_name
                        , quantity_per_unit
                        , unit_price
                        , units_in_stock
                        , units_on_order
                        , reorder_level
                    FROM products
                    WHERE product_id = ?
                    """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, productId);

        if(row.next())
        {
            int categoryId = row.getInt("category_id");
            String productName = row.getString("product_name");
            String quantityPerUnit = row.getString("quantity_per_unit");
            double unitPrice = row.getDouble("unit_price");
            int unitsInStock = row.getInt("units_in_stock");
            int unitsOnOrder = row.getInt("units_on_order");
            int reorderLevel = row.getInt("reorder_level");

            return new Product(productId, categoryId,productName,quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel);
        }
        return null;
    }

    public Product addProduct(Product product)
    {
        String sql = """
                    INSERT INTO products (product_name, category_id, quantity_per_unit, unit_price, units_in_stock, units_on_order, reorder_level)
                    VALUES (?, ?, ?, ?, ?, ?, ?);
                    """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getCategoryId());
            statement.setString(3, product.getQuantityPerUnit());
            statement.setDouble(4, product.getUnitPrice());
            statement.setInt(5, product.getUnitsInStock());
            statement.setInt(6, product.getUnitsOnOrder());
            statement.setInt(7, product.getReorderLevel());
            return statement;
        }, keyHolder);

        int newId = keyHolder.getKey().intValue();

        return getProductById(newId);
    }

    @Override
    public void updateProduct(int productId, Product product)
    {
        String sql = """
                    UPDATE products
                    SET product_name = ?
                      , category_id = ?
                      , quantity_per_unit = ?
                      , unit_price = ?
                      , units_in_stock = ?
                      , units_on_order = ?
                      , reorder_level = ?
                    WHERE product_id = ?;
                    """;

        jdbcTemplate.update(sql
                , product.getProductName()
                , product.getCategoryId()
                , product.getQuantityPerUnit()
                , product.getUnitPrice()
                , product.getUnitsInStock()
                , product.getUnitsOnOrder()
                , product.getReorderLevel()
                , product.getProductId());
    }

    @Override
    public void deleteProduct(int productId)
    {
        String sql = """
                    DELETE FROM products
                    WHERE product_id = ?;
                    """;

        jdbcTemplate.update(sql, productId);
    }
}
