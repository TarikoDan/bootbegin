package com.example.bootbegin.controllers;

import com.example.bootbegin.dto.request.UserRequest;
import com.example.bootbegin.dto.response.UserResponse;
import com.example.bootbegin.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    public IUserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create (@RequestBody @Valid UserRequest user) {
        return userService.save(user);
    }

    @GetMapping
    public List<UserResponse> getAll(){return userService.getAll();}

    @GetMapping("/{id}")
    public UserResponse getById (@PathVariable int id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public UserResponse edit (@PathVariable int id, @RequestBody @Valid UserRequest user) {
        return userService.edit(id, user);
    }

//    @DeleteMapping
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void clear() {
//
//    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void remove (@RequestParam String nickName) {
        if (nickName != null) {
            userService.remove(nickName);
        }else {
            userService.deleteAll();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById (@PathVariable int id ) {
        userService.deleteById(id);
    }

}
