package com.niantic.services;

import com.niantic.models.Vendor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class VendorDao
{
    private final JdbcTemplate jdbcTemplate;

    public VendorDao()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/budget");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addVendor(Vendor vendor)
    {
        String sql = """
                INSERT INTO vendors
                (vendor_name
                , website)
                VALUES
                (?, ?);
                """;

        jdbcTemplate.update(sql
                , vendor.getVendorName()
                , vendor.getWebsite());
    }
}
