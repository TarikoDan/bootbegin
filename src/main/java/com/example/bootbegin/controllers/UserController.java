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

    @GetMapping("/{email}")
    public UserResponse getByEmail (@PathVariable String email) {
        return userService.getByEmail(email);
    }

    @PutMapping("/{email}")
    public UserResponse edit (@PathVariable String email, @RequestBody @Valid UserRequest user) {
        return userService.edit(email, user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void remove (@RequestParam String email) {
        if (email != null) {
            userService.remove(email);
        }else {
            userService.deleteAll();
        }
    }

}
