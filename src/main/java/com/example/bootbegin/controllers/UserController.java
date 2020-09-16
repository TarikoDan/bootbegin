package com.example.bootbegin.controllers;

import com.example.bootbegin.entiti.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private final List<User> users = new ArrayList<>();

    @GetMapping("/users")
    public List<User> getAll () {
        return users;
    }

    @GetMapping("/users/{id}")
    public User getById (@PathVariable int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("no such User"));
    }
    @PostMapping("/users")
    public User create (@RequestBody User user) {
        users.add(user);
        return user;
    }
    @DeleteMapping("/users/{id}")
    public boolean delete (@PathVariable int id) {
        return users.removeIf(user -> user.getId() == id);
    }
    @DeleteMapping("/users")
    public void deleteAll () {
        users.clear();
    }
    @PutMapping("/users/{id}")
    public User edit (@PathVariable int id, @RequestBody User user) {
        users.stream()
                .filter(u -> u.getId() == id)
                .forEach(u -> {
                    u.setName(user.getName());
                    u.setSurName(user.getSurName());
                });
        return getById(id);
    }
}
