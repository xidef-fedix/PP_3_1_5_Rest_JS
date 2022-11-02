package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String users(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.getListUsers());
        model.addAttribute("userRole", roleService.getListRoles());
        model.addAttribute("user", user);
        return "users";
    }

    @GetMapping(value = "/new")
    public String newUser(User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("userRole", roleService.getListRoles());
        return "new";
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute("user") User user, @RequestParam("userRole") String roles) {
        List<String> listString = Arrays.stream(new String[]{roles}).toList();
        List<Role> listRole = userService.getListByRole(listString);
        user.setRoles(listRole);
        userService.add(user);
        return "redirect:/admin";
    }


    @PutMapping("/edit")
    public String updateUser(User user, @RequestParam("userRole") String[] roles) {
        List<String> listString = Arrays.stream(roles).toList();
        List<Role> listRole = userService.getListByRole(listString);
        user.setRoles(listRole);
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
