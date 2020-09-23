package com.example.bootbegin.controllersOld;

import com.example.bootbegin.dao.UserDAO;
import com.example.bootbegin.dto.request.UserRequest;
import com.example.bootbegin.dto.response.UserResponse;
import com.example.bootbegin.entiti.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uuusers")
public class UserController2 {
//    private final List<User> users = new ArrayList<>();
    @Autowired
    public UserDAO userDAO;

    @GetMapping
//    public List<User> getAll () {
//        return users;
//    }
    public List<User> getAll(){return userDAO.findAll();}

    @GetMapping("/{id}")
    public User getById (@PathVariable int id) {
        return userDAO
                .findById(id)
                .orElseThrow(() -> new NullPointerException("no such User with Id " + id));
    }
    @PostMapping
    public UserResponse create (@RequestBody UserRequest user) {
//        userDAO.save(user);


        UserResponse userResp = UserResponse.builder()
                .name(user.getName())
                .surName(user.getSurName())
                .build();
        return userResp;
    }
//    @DeleteMapping("/{id}")
//    public boolean delete (@PathVariable int id) {
//        return users.removeIf(user -> user.getId() == id);
//    }
//    @DeleteMapping
//    public void deleteAll () {
//        users.clear();
//    }
//    @PutMapping("/{id}")
//    public User edit (@PathVariable int id, @RequestBody User user) {
//        users.stream()
//                .filter(u -> u.getId() == id)
//                .forEach(u -> {
//                    u.setName(user.getName());
//                    u.setSurName(user.getSurName());
//                });
//        return getById(id);
//    }
}
