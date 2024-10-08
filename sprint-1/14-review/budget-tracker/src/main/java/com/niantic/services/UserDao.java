package com.niantic.services;

import com.niantic.models.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

public class UserDao
{
    private final JdbcTemplate jdbcTemplate;

    public UserDao()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/budget");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ArrayList<User> getAllUsers()
    {
        ArrayList<User> users = new ArrayList<User>();

        String sql = """
                SELECT user_id
                    , user_name
                    , first_name
                    , last_name
                    , phone
                    , email
                FROM users;
                """;

        var row = jdbcTemplate.queryForRowSet(sql);

        while(row.next())
        {
            int userId = row.getInt("user_id");
            String userName = row.getString("user_name");
            String firstName = row.getString("first_name");
            String lastName = row.getString("last_name");
            String phone = row.getString("phone");
            String email = row.getString("email");

            var user = new User(userId, userName, firstName, lastName, phone, email);
            users.add(user);
        }

        return users;
    }

    public User getUserById(int userId)
    {
        return null;
    }

    public User getUserByName(String name)
    {
        return null;
    }

    public void addUser(User user)
    {
        String sql = """
                INSERT INTO users
                (user_name
                , first_name
                , last_name
                , phone
                , email)
                VALUES
                (?, ?, ?, ?, ?);
                """;

        jdbcTemplate.update(sql
                , user.getUserName()
                , user.getFirstName()
                , user.getLastName()
                , user.getPhone()
                , user.getEmail());
    }

    public void updateUser(User user)
    {

    }

    public void deleteUser(int id)
    {
        String sql = "DELETE FROM users WHERE user_id = ?;";

        jdbcTemplate.update(sql, id);
    }
}
