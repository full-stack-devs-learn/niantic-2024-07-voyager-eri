package com.niantic.services;

import com.niantic.models.SubCategory;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class SubCategoryDao
{
    private final JdbcTemplate jdbcTemplate;

    public SubCategoryDao()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/budget");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addSubCategory(SubCategory subCategory)
    {
        String sql = """
                INSERT INTO sub_categories
                (category_id
                , sub_category_name
                , description)
                VALUES
                (?, ?, ?);
                """;

        jdbcTemplate.update(sql
                , subCategory.getCategoryId()
                , subCategory.getSubCategoryName()
                , subCategory.getDescription());
    }
}
