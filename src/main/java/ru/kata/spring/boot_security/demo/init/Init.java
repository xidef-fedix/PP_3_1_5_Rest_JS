//package ru.kata.spring.boot_security.demo.init;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.model.Role;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class Init {
//
//    private final UserService userService;
//    @Autowired
//    public Init(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostConstruct
//    public void init() {
//        Role role1 = new Role("ROLE_ADMIN");
//        Role role2 = new Role("ROLE_USER");
//
//        userService.addRole(role1);
//        userService.addRole(role2);
//
//        List<Role> roleAdmin = new ArrayList<>();
//        List<Role> roleUser = new ArrayList<>();
//
//        roleAdmin.add(role1);
//        roleUser.add(role2);
//
//        User user1 = new User("admin", "admin", "Админ", "Админов", roleAdmin);
//        User user2 = new User("user", "user", "Юзер", "Юзеров", roleUser);
//        User user3 = new User("user2", "user2", "ДаблЮзер", "ДаблЮзеров", roleUser);
//
//        userService.add(user1);
//        userService.add(user2);
//        userService.add(user3);
//    }
//}
