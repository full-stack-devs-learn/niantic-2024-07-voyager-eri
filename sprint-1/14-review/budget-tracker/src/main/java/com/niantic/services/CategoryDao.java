package com.niantic.services;

import com.niantic.models.Category;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class CategoryDao
{
    private final JdbcTemplate jdbcTemplate;

    public CategoryDao()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/budget");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addCategory(Category category)
    {
        String sql = """
                INSERT INTO categories
                (category_name
                , description)
                VALUES
                (?, ?);
                """;

        jdbcTemplate.update(sql
                , category.getCategoryName()
                , category.getDescription());
    }
}
