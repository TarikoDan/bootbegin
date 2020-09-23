package com.example.bootbegin.controllersOld;

import com.example.bootbegin.dao.UserDAO;
import com.example.bootbegin.dto.request.UserRequest;
import com.example.bootbegin.dto.response.UserResponse;
import com.example.bootbegin.entiti.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uusers")            /*!!!*/
public class UserController1 {
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
