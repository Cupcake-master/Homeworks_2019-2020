package ru.itis.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import ru.itis.model.User;
import ru.itis.service.UserService;
import ru.itis.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign_up")
    public String getSignUp(Model model){
        model.addAttribute("user", new User());
        return "/auth/sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute @Valid User user, BindingResult result) {
        //userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "/auth/sign_up";
        }
        userService.add(user);
        return "redirect:/users";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error,
                        Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        return "auth/sign_in";
    }
}
