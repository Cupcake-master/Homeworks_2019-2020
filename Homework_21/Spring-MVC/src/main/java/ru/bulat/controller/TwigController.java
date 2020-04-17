package ru.bulat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bulat.services.UsersService;

@Controller
@RequestMapping("/twig")
public class TwigController {

    private final UsersService usersService;

    @Autowired
    public TwigController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping
    public String index(ModelMap map) {
        map.put("message", "Bulat");
        return "index";
    }

    @RequestMapping("/users")
    public String users(ModelMap map) {
        map.put("users", usersService.findAll());
        return "users";
    }
}