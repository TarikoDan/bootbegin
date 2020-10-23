package com.example.bootbegin.controllers;

import com.example.bootbegin.dto.request.AuthRequest;
import com.example.bootbegin.dto.request.UserRequest;
import com.example.bootbegin.dto.response.AuthResponse;
import com.example.bootbegin.dto.response.UserResponse;
import com.example.bootbegin.services.IUserService;
import com.example.bootbegin.services.imp.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register (@RequestBody @Valid UserRequest user) {
        return userService.save(user);
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse generateJWT (@RequestBody @Valid AuthRequest authRequest) {
        System.out.println(authRequest.getEmail());
        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authRequest.getEmail(),authRequest.getPassword()
                        ));
        return new AuthResponse(jwtService.generateToken(authRequest.getEmail()));
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

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById (@PathVariable String email ) {
        userService.getByEmail(email);
    }

}
