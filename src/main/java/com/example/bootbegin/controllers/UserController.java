package com.example.bootbegin.controllers;

import com.example.bootbegin.entiti.User;
import com.example.bootbegin.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    public List<User> getAll(){return userService.getAll();}

    @GetMapping("/{id}")
    public User getById (@PathVariable int id) {
        return userService.getById(id);
    }

    @PostMapping
    public User create (@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User edit (@PathVariable int id, @RequestBody User user) {
        return userService.edit(id, user);
    }

    @DeleteMapping
    public void deleteAll () {
        userService.deleteAll();
    }

    @DeleteMapping("/{exp}")
    @Transactional
    public String deleteBy(@PathVariable String exp) {
        try {
            int i = Integer.parseInt(exp);
            return userService.DeleteById(i);
        }catch (NumberFormatException e) {
            return userService.DeleteByName(exp);
        }
    }
}
