package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;


@Controller
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(value = "/user")
    public String info(ModelMap model, Principal principal) {
        User userInfo = userDao.getByName(principal.getName());
        model.addAttribute("user", userInfo);
        return "user";
    }
}
