package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

    @RestController
    @RequestMapping("/api/users")
    public class RESTController {

        private final UserService userService;

        @Autowired
        public RESTController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<User>> pageForAdmin() {
            return new ResponseEntity<>(userService.getListUsers(), HttpStatus.OK);
        }

        @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<User> create(@RequestBody User user) {
            userService.add(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<User> get(@PathVariable("id") Long id) {
            if (userService.getById(id) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
        }

        @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<User> update(@RequestBody User user) {
            userService.update(user);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @DeleteMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<User> delete(@PathVariable("id") Long id) {
            if (userService.getById(id) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping()
        public ResponseEntity<User> getUserByUsername (Principal principal) {
            User user = userService.getByUsername(principal.getName());
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
}
