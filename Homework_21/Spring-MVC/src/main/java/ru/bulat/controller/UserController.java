package ru.bulat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bulat.model.User;
import ru.bulat.services.UsersService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class UserController {

    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users/{email:.+}")
    public User getUser(@PathVariable("email") String email){
        email+=".ru";
        return usersService.find(email);
    }

    @GetMapping("/users/all")
    public List<User> getUsers(){
        return usersService.findAll();
    }

    @PostMapping("/users/add")
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
        usersService.signUp(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/delete")
    public ResponseEntity<User> deleteUser(@RequestBody User user){
        usersService.delete(user);
        return ResponseEntity.ok().build();
    }
}
