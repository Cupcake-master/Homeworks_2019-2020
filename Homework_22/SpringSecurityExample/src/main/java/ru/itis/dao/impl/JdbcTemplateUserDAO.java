package ru.itis.dao.impl;

import ru.itis.dao.UserDAO;
import ru.itis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcTemplateUserDAO implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateUserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(
                "select * from users",
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    @Override
    public User getOne(String email) {
        return jdbcTemplate.query(
                "select * from users where email = ?",
                new Object[] { email },
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update(
                "insert into users values(?, ?, ?)",
                user.getName(), user.getSurname(), user.getEmail()
        );
    }
}
