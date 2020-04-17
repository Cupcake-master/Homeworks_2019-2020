package ru.bulat.repositories;

import ru.bulat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcTemplateUserRepositoryImpl implements UserRepository {

    private static final String SQL_DELETE_BY_EMAIL = "DELETE FROM iuser where email = ?";
    private static final String SQL_INSERT = "insert into iuser(email, password, telephone_number, date_of_birth, gender, country, about_myself) values(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_EMAIL = "select * from iuser where email = ?";
    private static final String SQL_SELECT_ALL = "select * from iuser";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(
                SQL_SELECT_ALL,
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    @Override
    public User find(String email) {
        return jdbcTemplate.query(
                SQL_SELECT_BY_EMAIL,
                new Object[] { email },
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);
    }

    @Override
    public User save(User user) {
        jdbcTemplate.update(
                SQL_INSERT,
                user.getEmail(), user.getPassword(), user.getTelephone_number(),
                user.getDateOfBirth(), user.getGender(), user.getCountry(),
                user.getAboutMyself()
        );
        return user;
    }

    @Override
    public void delete(User entity) {
        jdbcTemplate.update(SQL_DELETE_BY_EMAIL, entity);
    }
}
