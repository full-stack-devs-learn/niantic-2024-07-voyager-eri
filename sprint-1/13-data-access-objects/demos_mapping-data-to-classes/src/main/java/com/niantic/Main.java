package com.niantic;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Main
{
    public static void main(String[] args)
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        var jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = """
                SELECT actor_id
                     , first_name
                     , last_name
                FROM actor;
                """;

        var row = jdbcTemplate.queryForRowSet(sql);

        while(row.next())
        {
            int actorId = row.getInt("actor_id");
            String firstName = row.getString("first_name");
            String lastName = row.getString("last_name");

            System.out.println(STR."\{actorId} \{firstName} \{lastName}");
        }
    }
}