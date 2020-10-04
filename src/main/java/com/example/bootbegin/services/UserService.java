package com.example.bootbegin.services;

import com.example.bootbegin.entiti.User;
import com.example.bootbegin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepo;

    public User save(User user) {
        return userRepo.saveAndFlush(user);      /*saving new User to DB*/
    }
    public List<User> getAll () {
        return userRepo.findAll();
    }
    public User getById(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new NullPointerException("no such User with id " + id));
    }

    public User edit(int id, User user) {
        User foundedUser = userRepo.getOne(id);
//                .orElseThrow(() -> new NullPointerException("no such User with id" + id));
        foundedUser.setName(user.getName());
        foundedUser.setSurName(user.getSurName());
        userRepo.flush();
        return foundedUser;
    };

    public void deleteAll() {
        userRepo.deleteAll();
    }

    public String DeleteById(int id) {
        boolean exists = userRepo.existsById(id);
        userRepo.deleteById(id);
        return exists
                    ? "User with Id: " + id + " was deleted"
                    : "No such User with Id: " + id;
    }

    public String DeleteByName(String name) {
        boolean exists = userRepo.existsByName(name);
        userRepo.deleteUserByName(name);
        return exists
                    ? "User with Name: " + name + " was deleted"
                    : "No such User with Name: " + name;
    }
}
