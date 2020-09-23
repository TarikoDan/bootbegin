package com.example.bootbegin.controllers;

import com.example.bootbegin.dto.request.UserRequest;
import com.example.bootbegin.dto.response.UserResponse;
import com.example.bootbegin.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
//    private final List<User> users = new ArrayList<>();
    @Autowired
    public UserService userService;

    @GetMapping
    public List<UserResponse> getAll(){return userService.getAll();}

    @GetMapping("/{id}")
    public UserResponse getById (@PathVariable int id) {
        return userService.getById(id);
    }
    @PostMapping
    public UserResponse create (@RequestBody UserRequest user) {
        return userService.save(user);
    }
    @PutMapping("/{id}")
    public UserResponse edit (@PathVariable int id, @RequestBody UserRequest user) {
        return userService.edit(id, user);
    }
//    @DeleteMapping("/{id}")
//    public boolean delete (@PathVariable int id) {
//        return users.removeIf(user -> user.getId() == id);
//    }
//    @DeleteMapping
//    public void deleteAll () {
//        users.clear();
//    }
}
