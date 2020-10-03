package com.example.bootbegin.controllers;

import com.example.bootbegin.dao.UserDAO;
import com.example.bootbegin.entiti.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserDAO userDAO;

    @GetMapping
    public List<User> getAll(){return userDAO.findAll();}

    @GetMapping("/{id}")
    public User getById (@PathVariable int id) {
        return userDAO
                .findById(id)
                .orElseThrow(() -> new NullPointerException("no such User with Id " + id));
    }

    @PostMapping
    public User create (@RequestBody User user) {
        return userDAO.save(user);
    }

    @PutMapping("/{id}")
    public User edit (@PathVariable int id, @RequestBody User user) {
        User one = userDAO.getOne(id);
        one.setName(user.getName());
        one.setSurName(user.getSurName());
        return one;
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable int id) {
        userDAO.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll () {
        userDAO.deleteAll();
    }
}
