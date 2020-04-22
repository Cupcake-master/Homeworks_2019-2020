package ru.itis.dao;

import ru.itis.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();

    User getOne(String email);

    void add(User user);
}
