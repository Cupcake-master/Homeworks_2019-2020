package ru.itis.service;

import org.springframework.stereotype.Service;
import ru.itis.model.User;

import java.util.List;

@Service
public interface UserService {
    List<User> getAll();

    User getOne(String email);

    void add(User user);
}
