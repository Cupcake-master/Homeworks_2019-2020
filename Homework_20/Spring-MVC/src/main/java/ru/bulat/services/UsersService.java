package ru.bulat.services;

import ru.bulat.model.User;

import java.util.List;

public interface UsersService {
    void signUp(User user);
    List<User> findAll();
    User find(String email);
    void delete(User user);
    void add(User user);
}
