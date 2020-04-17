package ru.bulat.controller;

import org.springframework.transaction.annotation.Transactional;
import ru.bulat.model.User;
import ru.bulat.services.UsersService;
import ru.bulat.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

    private final UserValidator userValidator;
    private final UsersService usersService;

    @Autowired
    public MainController(UserValidator userValidator, UsersService usersService) {
        this.userValidator = userValidator;
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", usersService.findAll());
        return "users";
    }

    @GetMapping("/users/new")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "sign_up";
    }

    @PostMapping("/users/new")
    @Transactional
    public String signUp(@ModelAttribute @Valid User user, BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "sign_up";
        }
        usersService.signUp(user);
        return "redirect:/users";
    }
}
