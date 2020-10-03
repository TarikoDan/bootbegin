package com.example.bootbegin.controllers;

import com.example.bootbegin.entiti.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getAll () { return users; }

    @GetMapping("/{id}")
    public User getById (@PathVariable int id) {
        Optional<User> first = users.stream()
                .filter(user -> user.getId() == id).findFirst();
        return first.orElse(null);
    }

    @PostMapping
    public User create (@RequestBody User user) {
        users.add(user);
        return users.get(users.size()-1);
    }

    @PutMapping("/{id}")
    public User edit (@PathVariable int id, @RequestBody User user) {
        User edited = users.get(id);
        edited.setName(user.getName());
        edited.setSurName(user.getSurName());
        return edited;
    }
    @DeleteMapping("/{id}")
    public String delete (@PathVariable int id) {
        return
                users.removeIf(user -> user.getId() == id)
                        ? "User with id: " + id + "deleted"
                        :"No such User with Id: " + id;
    }

    @DeleteMapping
    public void deleteAll () {
        users.clear();
    }
}
